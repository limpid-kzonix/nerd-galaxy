package io.kzonix.boardgamegeek

import com.google.inject.AbstractModule
import io.kzonix.boardgamegeek.config.AppConfig
import net.codingwell.scalaguice.ScalaModule

/** The main components of the server application */
class ServerApplicationConfigModule(appConfig: AppConfig) extends AbstractModule with ScalaModule {
  override def configure(): Unit =
    bind[AppConfig].toInstance(appConfig)
}
