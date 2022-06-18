package io.kzonix.boardgamegeek.model.http

import io.circe.generic.extras.ConfiguredJsonCodec
import io.circe.generic.extras.Configuration
import io.kzonix.boardgamegeek.util.CirceUtils.DefaultCirceConfig

@ConfiguredJsonCodec
case class ApiWarningMap(
    data: List[Warning] = List.empty
  )

object ApiWarningMap {
  implicit val conf: Configuration = DefaultCirceConfig
}
