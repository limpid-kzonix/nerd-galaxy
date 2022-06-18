package io.kzonix.boardgamegeek.model.http

import io.circe.generic.extras.ConfiguredJsonCodec
import io.circe.generic.extras.Configuration
import io.kzonix.boardgamegeek.CirceUtils.DefaultConfig

@ConfiguredJsonCodec
case class Warning(
    name: String,
    msg: String,
    details: Map[String, String] = Map.empty
)

object Warning {

  implicit val conf: Configuration = DefaultConfig
}
