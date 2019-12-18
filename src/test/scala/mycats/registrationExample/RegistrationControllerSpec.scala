package mycats.registrationExample

import org.scalatest._

class RegistrationControllerSpec extends FlatSpec with Matchers {

  val controller = new RegistrationController(
    RequestParserCirce,
    FormValidatorCats
  )

  "RegistrationController.handleRequest" should "be ok" in {

    val request =
      """|{
         |  "username": "Joe",
         |  "password": "Passw0r$1234",
         |  "firstName": "John",
         |  "lastName": "Doe",
         |  "age": 21
         |}
         |""".stripMargin

    val result = controller.handleRequest(request)
    val answer = "ok"

    result shouldEqual answer

  }

  "RegistrationController.handleRequest" should "show domain error" in {

    val request =
      """|{
         |  "username": "Joe%%%",
         |  "password": "password",
         |  "firstName": "John",
         |  "lastName": "Doe",
         |  "age": 21
         |}
         |""".stripMargin

    val result = controller.handleRequest(request)
    val answer =
      """|Username cannot contain special characters.
         |Password must be at least 10 characters long, including an uppercase and a lowercase letter, one number and one special character.""".stripMargin

    result shouldEqual answer

  }

  "RegistrationController.handleRequest" should "show parsing error" in {

    val request =
      """|{
         |  "username": "Joe",
         |  "password": "Passw0r$1234",
         |  "firstName": "John",
         |  "age": "21"
         |}
         |""".stripMargin

    val result = controller.handleRequest(request)
    val answer = "" // FIXME

    result shouldEqual answer

  }

}
