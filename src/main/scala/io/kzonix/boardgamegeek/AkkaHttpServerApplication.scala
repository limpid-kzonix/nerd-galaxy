package io.kzonix.boardgamegeek

import akka.actor.typed.ActorSystem
import akka.actor.typed.SpawnProtocol
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.Http
import io.kzonix.boardgamegeek.routes.ApplicationRouter

import scala.concurrent.ExecutionContext
import javax.inject.Inject

class AkkaHttpServerApplication @Inject() (
    config: Config,
    system: ActorSystem[SpawnProtocol.Command],
    router: ApplicationRouter,
  ) extends ServerApplication
       with LazyLogging {
  implicit val as: ActorSystem[SpawnProtocol.Command] = system
  implicit val executor: ExecutionContext             = system.executionContext

  def start(): Unit = {
    logger.info("Starting Akka HTTP server instance...")
    logger.debug(config.root().render())

    Http()
      .newServerAt(
        config.getString("http.interface"),
        config.getInt("http.port"),
      )
      .bindFlow(router.routes)

    system.terminate()
  }

  def stop(): Unit = {}
}

object AkkaHttpServerApplication {}
