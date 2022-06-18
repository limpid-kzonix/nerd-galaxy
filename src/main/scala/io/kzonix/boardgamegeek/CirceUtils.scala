package io.kzonix.boardgamegeek

import io.circe.generic.extras.Configuration

object CirceUtils {

  val DefaultConfig: Configuration = Configuration.default.withDefaults.withSnakeCaseMemberNames
    .withDiscriminator("kind")

}
