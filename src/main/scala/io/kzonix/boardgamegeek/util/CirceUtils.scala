package io.kzonix.boardgamegeek.util

object CirceUtils {
  import io.circe.generic.extras.Configuration

  val DefaultCirceConfig: Configuration = Configuration
    .default
    .withDefaults
    .withSnakeCaseMemberNames
    .withDiscriminator("kind")
}
