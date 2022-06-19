package io.kzonix.boardgamegeek.service.auth

import cats.data.EitherT
import io.kzonix.boardgamegeek.exceptions.AppRuntimeError
import io.kzonix.boardgamegeek.service.auth.AuthenticationService.UserContext
import cats.effect.IO

trait AuthenticationService {
  def authenticate(authToken: String): EitherT[IO, AppRuntimeError, UserContext]
  def canApply(authToken: String): EitherT[IO, AppRuntimeError, Boolean]
}

object AuthenticationService {
  sealed trait UserContext

  case object Empty extends UserContext

  case class UserIdentity(
      id: String,
      name: String,
      email: String,
    ) extends UserContext
}
