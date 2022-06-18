package io.kzonix.boardgamegeek

import com.google.inject.AbstractModule
import io.kzonix.boardgamegeek.config.RootConfig
import net.codingwell.scalaguice.ScalaModule

/** The main components of the server application */
class ServerApplicationConfigModule(appConfig: RootConfig) extends AbstractModule with ScalaModule {
  override def configure(): Unit =
    bind[RootConfig].toInstance(appConfig)
}
