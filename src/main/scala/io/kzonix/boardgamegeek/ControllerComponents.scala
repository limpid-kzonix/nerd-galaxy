package io.kzonix.boardgamegeek

import com.typesafe.config.Config
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.kzonix.boardgamegeek.model.http.ApiResponse

class ControllerComponents(config: Config) {
  val baseEndpoint: Endpoint[Unit, Unit, (StatusCode, ApiResponse[String]), Unit, Any] = endpoint
    .in("api" / "v1.0")
    .errorOut(statusCode.and(jsonBody[ApiResponse[String]]))

  val secureEndpoint: Endpoint[String, Unit, (StatusCode, ApiResponse[String]), Unit, Any] =
    baseEndpoint
      .securityIn(auth.bearer[String]())
      .in("secure")

  val publicEndpoint: Endpoint[Unit, Unit, (StatusCode, ApiResponse[String]), Unit, Any] =
    baseEndpoint
      .in("public")
}
