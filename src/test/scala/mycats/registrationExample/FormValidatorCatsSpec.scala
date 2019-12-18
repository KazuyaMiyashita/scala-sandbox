package mycats.registrationExample

import org.scalatest._

import mycats.registrationExample.DomainValidation._

class FormValidatorCatsSpec extends FlatSpec with Matchers {

  "FormValidatorCats.validateForm" should "be Right" in {

    val result: Either[List[DomainValidation], RegistrationData] = FormValidatorCats.validateForm(
      username = "Joe",
      password = "Passw0r$1234",
      firstName = "John",
      lastName = "Doe",
      age = 21
    )
    val answer = Right(RegistrationData("Joe", "Passw0r$1234", "John", "Doe", 21))

    result shouldEqual answer
  }

  "FormValidatorCats.validateForm" should "be Left" in {

    val result: Either[List[DomainValidation], RegistrationData] = FormValidatorCats.validateForm(
      username = "Joe%%%",
      password = "password",
      firstName = "John",
      lastName = "Doe",
      age = 21
    )
    val answer = Left(List(UsernameHasSpecialCharacters, PasswordDoesNotMeetCriteria))

    result shouldEqual answer
  }

}
