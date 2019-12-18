package mycats.registrationExample

import cats.data._
import cats.data.Validated._
import cats.implicits._

object FormValidatorCats extends FormValidator {

  private type ValidationResult[A] = Validated[NonEmptyChain[DomainValidation], A]

  private def toValidated[A](e: Either[DomainValidation, A]): ValidationResult[A] = {
    Validated.fromEither(e).leftMap(NonEmptyChain(_))
  }

  private def validateUserName(userName: String): ValidationResult[String] = {
    toValidated(validateUserNameEither(userName))
  }

  private def validatePassword(password: String): ValidationResult[String] = {
    toValidated(validatePasswordEither(password))
  }

  private def validateFirstName(firstName: String): ValidationResult[String] = {
    toValidated(validateFirstNameEither(firstName))
  }

  private def validateLastName(lastName: String): ValidationResult[String] = {
    toValidated(validateLastNameEither(lastName))
  }

  private def validateAge(age: Int): ValidationResult[Int] = {
    toValidated(validateAgeEither(age))
  }

  override def validateForm(
      username: String,
      password: String,
      firstName: String,
      lastName: String,
      age: Int
  ): Either[List[DomainValidation], RegistrationData] = {
    val v: ValidationResult[RegistrationData] = (
      validateUserName(username),
      validatePassword(password),
      validateFirstName(firstName),
      validateLastName(lastName),
      validateAge(age)
    ).mapN(RegistrationData)

    v.toEither.leftMap(_.toList)
  }

}
