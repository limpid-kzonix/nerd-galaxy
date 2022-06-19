package io.kzonix.boardgamegeek

import akka.actor.typed.ActorSystem
import akka.actor.typed.SpawnProtocol
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.Http
import io.kzonix.boardgamegeek.routes.ApplicationRouter

import scala.concurrent.ExecutionContext
import javax.inject.Inject
import scala.concurrent.Future

class AkkaHttpServerApplication @Inject() (
    config: Config,
    system: ActorSystem[SpawnProtocol.Command],
    router: ApplicationRouter)
    extends ServerApplication
       with LazyLogging {

  implicit val as: ActorSystem[SpawnProtocol.Command] = system
  implicit val executor: ExecutionContext             = system.executionContext

  def start(): Unit = {
    import akka.actor.CoordinatedShutdown
    import akka.actor.CoordinatedShutdown.{ PhaseActorSystemTerminate, PhaseBeforeServiceUnbind }

    import scala.concurrent.Await
    import scala.concurrent.duration.Duration
    logger.info("Starting Akka HTTP server instance...")
    logger.debug(config.root().render())

    val serverBinding: Future[Http.ServerBinding] = for {
      serverBinding <- startHttpServer
      _             <- Future(logger.info(s"The server has been successfully started: ${ serverBinding.toString }."))
    } yield serverBinding

    Await.ready(
      serverBinding,
      Duration.Inf,
    )

    CoordinatedShutdown(as).addTask(
      PhaseBeforeServiceUnbind,
      "clean-up-server-resources",
    ) { () =>
      Future {
        import akka.Done
        logger.info("Before server termination")
        Done.done()
      }
    }

    CoordinatedShutdown(as).addTask(
      PhaseActorSystemTerminate,
      "clean-up-server-resources",
    ) { () =>
      Future {
        import akka.Done
        logger.info("Actor system termination")
        Thread.sleep(35000)
        Done.done()
      }
    }
    ()
  }

  private def startHttpServer: Future[Http.ServerBinding] =
    Http()
      .newServerAt(
        config.getString("application.server.http.interface"),
        config.getInt("application.server.http.port"),
      )
      .bindFlow(router.routes)

}

object AkkaHttpServerApplication {}
