package io.kzonix.boardgamegeek.config

sealed trait AuthConfig

object AuthConfig {
  sealed trait BearerAuth
  case class Jwt(privateKey: String) extends BearerAuth

  sealed trait BasicAuth
  case class Predefined(creds: List[String]) extends BasicAuth

  sealed trait ApiKeyAuth
  case class PredefKeys(keys: List[String]) extends ApiKeyAuth

  case class Bearer(provider: BearerAuth) extends AuthConfig
  case class Basic(provider: BasicAuth)   extends AuthConfig
  case class ApiKey(provider: ApiKeyAuth) extends AuthConfig
}
