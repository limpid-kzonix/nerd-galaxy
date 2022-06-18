package io.kzonix.boardgamegeek.model.http

import io.circe.generic.extras.Configuration
import io.circe.Decoder
import io.circe.Encoder
import io.circe.generic.extras.semiauto.deriveConfiguredDecoder
import io.circe.generic.extras.semiauto.deriveConfiguredEncoder
import io.kzonix.boardgamegeek.util.CirceUtils.DefaultCirceConfig

case class ApiSuccessResponse[T](
    data: Option[T],
    warns: Option[ApiWarningMap] = None,
  )

object ApiSuccessResponse {
  implicit val conf: Configuration = DefaultCirceConfig

  @annotation.nowarn
  implicit def ApiResponseEncoder[T: Encoder]: Encoder[ApiSuccessResponse[T]] =
    deriveConfiguredEncoder

  @annotation.nowarn
  implicit def ApiResponseDecoder[T: Decoder]: Decoder[ApiSuccessResponse[T]] =
    deriveConfiguredDecoder
}
