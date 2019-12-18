package mycats.registrationExample

import org.scalatest._

import cats.data.Validated.{Valid, Invalid}
import cats.data.Chain

import mycats.registrationExample.DomainValidation._

class HelloSpec extends FlatSpec with Matchers {

  "FormValidationNec.validateForm" should "be Vaild" in {

    val result: FormValidatorNec.ValidationResult[RegistrationData] = FormValidatorNec.validateForm(
      username = "Joe",
      password = "Passw0r$1234",
      firstName = "John",
      lastName = "Doe",
      age = 21
    )
    val answer = Valid(RegistrationData("Joe", "Passw0r$1234", "John", "Doe", 21))

    result shouldEqual answer
  }

  "FormValidationNec.validateForm" should "be Invalid" in {

    val result: FormValidatorNec.ValidationResult[RegistrationData] = FormValidatorNec.validateForm(
      username = "Joe%%%",
      password = "password",
      firstName = "John",
      lastName = "Doe",
      age = 21
    )
    val answer = Invalid(Chain(UsernameHasSpecialCharacters, PasswordDoesNotMeetCriteria))

    result shouldEqual answer
  }

}
