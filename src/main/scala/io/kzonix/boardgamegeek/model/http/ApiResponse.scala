package io.kzonix.boardgamegeek.model.http

import io.circe.generic.extras.Configuration
import io.circe.Decoder
import io.circe.Encoder
import io.circe.generic.extras.semiauto.deriveConfiguredDecoder
import io.circe.generic.extras.semiauto.deriveConfiguredEncoder
import io.kzonix.boardgamegeek.CirceUtils.DefaultConfig

case class ApiResponse[T](
    data: Option[T],
    errorPayload: Option[ApiErrorMap] = None
)

object ApiResponse {

  implicit val conf: Configuration = DefaultConfig

  @annotation.nowarn
  implicit def ApiResponseEncoder[T: Encoder]: Encoder[ApiResponse[T]] = deriveConfiguredEncoder

  @annotation.nowarn
  implicit def ApiResponseDecoder[T: Decoder]: Decoder[ApiResponse[T]] = deriveConfiguredDecoder
}
