package io.kzonix.boardgamegeek.model.http

import io.circe.generic.extras.ConfiguredJsonCodec
import io.circe.generic.extras.Configuration
import io.kzonix.boardgamegeek.util.CirceUtils.DefaultCirceConfig

@ConfiguredJsonCodec
case class ApiErrorResponse(
    errors: List[ApiError] = List.empty)

object ApiErrorResponse {
  implicit val conf: Configuration = DefaultCirceConfig
}
