package io.kzonix.boardgamegeek

import com.typesafe.scalalogging.LazyLogging
import com.typesafe.config.ConfigFactory
import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

object Main extends LazyLogging {
  def main(args: Array[String]): Unit = {
    logger.info("Loading configuration...")
    val config          = ConfigFactory.load()
    logger.info("The configuration has been successfully loaded.")
    val serverAppModule = new ServerApplicationModule(config)
    logger.info(s"Wiring dependencies: ${ serverAppModule.getClass.getSimpleName }")
    val injector        = Guice.createInjector(serverAppModule)
    val serverApp       = injector.instance[ServerApplication]
    serverApp.start()
  }
}
