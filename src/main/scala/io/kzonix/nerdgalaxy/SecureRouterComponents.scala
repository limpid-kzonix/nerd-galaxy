package io.kzonix.nerdgalaxy

import com.typesafe.scalalogging.LazyLogging
import io.kzonix.nerdgalaxy.model.http.ApiErrorResponse
import io.kzonix.nerdgalaxy.SecureRouterComponents.EmptyUserContext
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.server.PartialServerEndpoint

import scala.concurrent.Future
import javax.inject.Inject
import io.kzonix.nerdgalaxy.SecureRouterComponents.UserContext

// TODO: add service based approach to deal with 'serverSecurityLogic'
class SecureRouterComponents @Inject() (
    controllerComponents: RouterComponents)
    extends LazyLogging {

  val secureEndpoint
      : PartialServerEndpoint[String, UserContext, Unit, (StatusCode, ApiErrorResponse), Unit, Any, Future] =
    controllerComponents
      .baseEndpoint
      .securityIn(auth.bearer[String]())
      .in("secure")
      .serverSecurityLogic[UserContext, Future] { auth =>
        logger.info(auth)
        Future.successful(Right.apply[(StatusCode, ApiErrorResponse), UserContext](EmptyUserContext))
      }

}

object SecureRouterComponents {

  trait UserContext
  case object EmptyUserContext extends UserContext

}
