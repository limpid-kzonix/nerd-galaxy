package io.kzonix.boardgamegeek

import com.typesafe.scalalogging.LazyLogging
import io.kzonix.boardgamegeek.config.RootConfig
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.kzonix.boardgamegeek.model.http.ApiErrorResponse
import io.kzonix.boardgamegeek.SecureRouterComponents.EmptyUserContext
import io.kzonix.boardgamegeek.SecureRouterComponents.UserContext
import sttp.tapir.server.PartialServerEndpoint

import scala.concurrent.Future
import javax.inject.Inject

class RouterComponents @Inject() (appConfig: RootConfig) extends LazyLogging {
  val baseEndpoint: Endpoint[Unit, Unit, (StatusCode, ApiErrorResponse), Unit, Any] = endpoint
    .in("api" / "v1")
    .errorOut(statusCode.and(jsonBody[ApiErrorResponse]))

  val secureEndpoint
      : PartialServerEndpoint[String, UserContext, Unit, (StatusCode, ApiErrorResponse), Unit, Any, Future] =
    baseEndpoint
      .securityIn(auth.bearer[String]())
      .in("secure")
      .serverSecurityLogic[UserContext, Future] { auth =>
        logger.info(auth)
        logger.info(appConfig.toString)
        Future.successful(Right.apply[(StatusCode, ApiErrorResponse), UserContext](EmptyUserContext))
      }

  val publicEndpoint: Endpoint[Unit, Unit, (StatusCode, ApiErrorResponse), Unit, Any] =
    baseEndpoint
      .in("public")
}
