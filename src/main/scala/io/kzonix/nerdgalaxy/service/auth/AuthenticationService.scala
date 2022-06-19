package io.kzonix.nerdgalaxy.service.auth

import cats.data.EitherT
import io.kzonix.nerdgalaxy.exceptions.AppRuntimeError
import io.kzonix.nerdgalaxy.service.auth.AuthenticationService.UserContext
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
      email: String)
      extends UserContext

}
