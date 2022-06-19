package io.kzonix.boardgamegeek

import com.google.inject.AbstractModule
import io.kzonix.boardgamegeek.config.RootConfig
import net.codingwell.scalaguice.ScalaModule

/** The static config instances */
class ServerApplicationConfigModule(rootConfig: RootConfig) extends AbstractModule with ScalaModule {

  import io.kzonix.boardgamegeek.config.ApplicationConfig

  override def configure(): Unit = {
    import io.kzonix.boardgamegeek.config.DatabaseConfig
    bind[RootConfig].toInstance(rootConfig)
    bind[ApplicationConfig].toInstance(rootConfig.application)
    bind[DatabaseConfig].toInstance(rootConfig.database)
  }

}
