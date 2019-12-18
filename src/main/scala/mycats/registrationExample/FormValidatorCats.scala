package mycats.registrationExample

import cats.data._
import cats.data.Validated._
import cats.implicits._

object FormValidatorCats extends FormValidator {

  override def validateForm(
      username: String,
      password: String,
      firstName: String,
      lastName: String,
      age: Int
  ): Either[List[DomainValidation], RegistrationData] = {

    val v: ValidatedNec[DomainValidation, RegistrationData] = (
      validateUserName(username).toValidatedNec,
      validatePassword(password).toValidatedNec,
      validateFirstName(firstName).toValidatedNec,
      validateLastName(lastName).toValidatedNec,
      validateAge(age).toValidatedNec
    ).mapN(RegistrationData)

    v.toEither.leftMap(_.toList)
  }

}
