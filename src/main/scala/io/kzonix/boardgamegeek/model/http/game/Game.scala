package io.kzonix.boardgamegeek.model.http.game

import io.circe.generic.extras.ConfiguredJsonCodec
import io.circe.generic.extras.Configuration
import io.kzonix.boardgamegeek.util.CirceUtils.DefaultCirceConfig

@ConfiguredJsonCodec
case class Game(
    id: String,
    name: String,
    createdAt: Long,
  )

object Game {
  implicit val conf: Configuration = DefaultCirceConfig
}
