package mycats.registrationExample

class RegistrationController(
    parser: RequestParser,
    validator: FormValidator
) {

  def handleRequest(request: String): String = {

    val result = for {
      form <- parser.parse(request).left.map(_.err)
      validated <- validator
        .validateForm(form.username, form.password, form.firstName, form.lastName, form.age)
        .left
        .map(_.map(_.errorMessage).mkString("\n"))
    } yield validated

    result match {
      case Left(err) => err
      case Right(_)  => "ok"
    }

  }

}
