package mycats.registrationExample

trait RequestParser {

  import RequestParser._

  def parse(request: String): Either[ParseError, Form]

}

object RequestParser {

  case class ParseError(err: String)
  case class Form(
      username: String,
      password: String,
      firstName: String,
      lastName: String,
      age: Int
  )

}
