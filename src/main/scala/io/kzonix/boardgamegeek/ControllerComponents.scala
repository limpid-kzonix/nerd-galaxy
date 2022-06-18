package io.kzonix.boardgamegeek

import io.kzonix.boardgamegeek.config.AppConfig
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.kzonix.boardgamegeek.model.http.ApiErrorResponse

import javax.inject.Inject

class ControllerComponents @Inject() (appConfig: AppConfig) {
  val baseEndpoint: Endpoint[Unit, Unit, (StatusCode, ApiErrorResponse), Unit, Any] = endpoint
    .in("api" / s"v${ appConfig.apiVersion }")
    .errorOut(statusCode.and(jsonBody[ApiErrorResponse]))

  val secureEndpoint: Endpoint[String, Unit, (StatusCode, ApiErrorResponse), Unit, Any] =
    baseEndpoint
      .securityIn(auth.bearer[String]())
      .in("secure")

  val publicEndpoint: Endpoint[Unit, Unit, (StatusCode, ApiErrorResponse), Unit, Any] =
    baseEndpoint
      .in("public")
}
