package io.kzonix.boardgamegeek.model.http

import io.circe.generic.extras.ConfiguredJsonCodec
import io.circe.generic.extras.Configuration
import io.kzonix.boardgamegeek.CirceUtils.DefaultConfig

@ConfiguredJsonCodec
case class ApiErrorMap(
    errors: List[ApiError] = List.empty,
    warns: List[Warning] = List.empty
)

object ApiErrorMap {

  implicit val conf: Configuration = DefaultConfig

}
