package io.kzonix.nerdgalaxy.exceptions

import io.kzonix.nerdgalaxy.exceptions.AppError.BaseErrorDef
import io.kzonix.nerdgalaxy.exceptions.AppError.ErrorDef

object errors {

  object auth {

    val NoSupportedAuth: BaseErrorDef = BaseErrorDef(
      code = "1",
      module = "auth",
      message = "The authentication type can not be determined.",
    )

  }

  val UserNotFound: ErrorDef = BaseErrorDef(
    code = "1",
    module = "user",
    message = "UserNotFound",
  )

}
