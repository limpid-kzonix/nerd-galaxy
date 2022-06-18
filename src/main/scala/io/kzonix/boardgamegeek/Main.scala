package io.kzonix.boardgamegeek

import com.typesafe.scalalogging.LazyLogging
import com.typesafe.config.ConfigFactory

object Main extends LazyLogging {
  def main(args: Array[String]): Unit = {
    import com.google.inject.Guice
    import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

    logger.info("Loading configuration...")
    val config          = ConfigFactory.load()
    val serverAppModule = new ServerApplicationModule(config)
    logger.info(serverAppModule.toString)
    val injector        = Guice.createInjector(serverAppModule)
    val serverApp       = injector.instance[ServerApplication]
    logger.info("Starting the application...")
    serverApp.start()
  }
}
