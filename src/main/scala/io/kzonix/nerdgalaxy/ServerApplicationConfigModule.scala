package io.kzonix.nerdgalaxy

import com.google.inject.AbstractModule
import io.kzonix.nerdgalaxy.config.RootConfig
import net.codingwell.scalaguice.ScalaModule

/** The static config instances */
class ServerApplicationConfigModule(rootConfig: RootConfig) extends AbstractModule with ScalaModule {

  import io.kzonix.nerdgalaxy.config.ApplicationConfig

  override def configure(): Unit = {
    import io.kzonix.nerdgalaxy.config.DatabaseConfig
    bind[RootConfig].toInstance(rootConfig)
    bind[ApplicationConfig].toInstance(rootConfig.application)
    bind[DatabaseConfig].toInstance(rootConfig.database)
  }

}
