package io.kzonix.boardgamegeek.model.http

import io.circe.Json
import io.circe.generic.extras.ConfiguredJsonCodec
import io.circe.generic.extras.Configuration
import io.kzonix.boardgamegeek.CirceUtils.DefaultConfig

@ConfiguredJsonCodec
case class ApiError(
    module: String,
    code: String,
    msg: String,
    details: Map[String, String] = Map.empty,
    typedDetails: Json = Json.obj()
)

object ApiError {

  implicit val conf: Configuration = DefaultConfig

}
