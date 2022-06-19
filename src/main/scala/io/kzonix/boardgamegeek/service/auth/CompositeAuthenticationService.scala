package io.kzonix.boardgamegeek.service.auth

import cats.data.EitherT
import cats.effect.IO
import io.kzonix.boardgamegeek.exceptions.AppRuntimeError
import io.kzonix.boardgamegeek.service.auth.AuthenticationService.UserContext
import io.kzonix.boardgamegeek.exceptions.AppError
import io.kzonix.boardgamegeek.exceptions.errors.auth.NoSupportedAuth
import cats.syntax.monad._
import com.typesafe.scalalogging.LazyLogging
import io.kzonix.boardgamegeek.service.auth.CompositeAuthenticationService.Execution

// TODO: replace EitherT with Kleisli to always trace synthetic/passed data (request-id/trace-id/user-ctx)
class CompositeAuthenticationService(authenticationServices: Set[AuthenticationService])
    extends AuthenticationService
       with LazyLogging {
  override def authenticate(authToken: String): EitherT[IO, AppRuntimeError, UserContext] = {

    val execResult: IO[Execution] = Execution(
      authToken,
      authenticationServices,
      List.empty,
      None,
    ).iterateUntilM(performAction)(_.result.nonEmpty)

    EitherT(execResult.map { executionResult =>
      executionResult.result.toRight {
        val error = executionResult.exceptionTrace.head
        logger.error("Failed to authenticate user request")
        error
      }
    })
  }

  override def canApply(authToken: String): EitherT[IO, AppRuntimeError, Boolean] =
    EitherT.rightT(true)

  private def performAction(exec: Execution): IO[Execution] = {
    val services = exec.services
    if (services.nonEmpty) {
      val current                                              = services.head
      val triedAuth: EitherT[IO, AppRuntimeError, UserContext] = for {
        canApply <- current.canApply(exec.token)
        res      <- if (canApply) current.authenticate(exec.token)
                    else EitherT.leftT[IO, UserContext](AppError(NoSupportedAuth))
      } yield res

      triedAuth.value.map {
        case Left(ex)                        =>
          exec.copy(
            services = services.tail,
            exceptionTrace = ex :: exec.exceptionTrace,
          )
        case Right(userContext: UserContext) =>
          exec.copy(
            services = services.tail,
            result = Some(userContext),
          )
      }
    }
    else
      IO.pure(exec)
  }
}

object CompositeAuthenticationService {
  case class Execution(
      token: String,
      services: Set[AuthenticationService],
      exceptionTrace: List[AppRuntimeError],
      result: Option[UserContext],
    )
}
