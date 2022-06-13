package io.kzonix.boardgamegeek.model

case class ApiResponse[T](
    data: Option[T],
    errorPayload: Option[AppErrorMap] = None
)

object ApiResponse {
  @annotation.nowarn
  implicit def ApiResponseEncoder[T: Encoder]: Encoder[ApiResponse[T]] = deriveConfiguredEncoder

  @annotation.nowarn
  implicit def ApiResponseDecoder[T: Decoder]: Decoder[ApiResponse[T]] = deriveConfiguredDecoder
}
