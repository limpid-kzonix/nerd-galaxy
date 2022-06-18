package io.kzonix.boardgamegeek

import com.google.inject.AbstractModule
import com.typesafe.config.Config
import net.codingwell.scalaguice.ScalaModule
import com.google.inject.Singleton
import akka.actor.typed.ActorSystem
import akka.actor.typed.SpawnProtocol
import pureconfig.generic.auto._
import io.kzonix.boardgamegeek.config.AppConfig
import pureconfig.ConfigSource

/** The main components of the server application */
class ServerApplicationModule(config: Config) extends AbstractModule with ScalaModule {
  import io.kzonix.boardgamegeek.config.AppConfig
  import io.kzonix.boardgamegeek.ServerApplicationModule.createActorSystem
  import io.kzonix.boardgamegeek.ServerApplicationModule.decodeConfig

  private val appConfig: AppConfig                       = decodeConfig(config)
  private val system: ActorSystem[SpawnProtocol.Command] = createActorSystem(
    appConfig.appName,
    config,
  )

  override def configure(): Unit = {
    import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter

    import scala.concurrent.ExecutionContext
    bind[Config].toInstance(config)
    // instantiate dependencies for all possible typed configurations
    install(new ServerApplicationConfigModule(appConfig))

    bind[ServerApplication].to[AkkaHttpServerApplication].in[Singleton]()
    bind[ControllerComponents].asEagerSingleton()
    bind[ActorSystem[SpawnProtocol.Command]].toInstance(system)
    bind[ExecutionContext].toInstance(system.executionContext)
    bind[AkkaHttpServerInterpreter].toInstance(AkkaHttpServerInterpreter()(system.executionContext))

  }
}

object ServerApplicationModule {
  private def decodeConfig(config: Config) =
    ConfigSource
      .fromConfig(config)
      .withFallback(ConfigSource.default)
      .loadOrThrow[AppConfig]

  private def createActorSystem(appName: String, config: Config) =
    ActorSystem.create(
      GuardianAkkaHttpServerApplicationActor(),
      appName,
      config,
    )
}
