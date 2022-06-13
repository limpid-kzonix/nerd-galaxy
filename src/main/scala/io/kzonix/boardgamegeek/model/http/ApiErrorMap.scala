package io.kzonix.boardgamegeek.model.http

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec
case class AppErrorMap(
    errors: List[ApiError] = List.empty,
    warns: List[Warning] = List.empty
)
