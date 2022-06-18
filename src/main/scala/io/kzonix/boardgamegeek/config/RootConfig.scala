package io.kzonix.boardgamegeek.config

case class RootConfig(
    appName: String,
    application: ApplicationConfig,
    database: DatabaseConfig,
  ) {}
