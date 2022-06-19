package io.kzonix.boardgamegeek.config

case class HttpConfig(
    interface: String,
    port: Int,
    basePath: String = "/",
    apiVersion: Int = 1,
    auth: AuthConfig) {}
