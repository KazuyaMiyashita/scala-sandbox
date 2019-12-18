package mycats.registrationExample

import io.circe._
import io.circe.parser

object RequestParserCirce extends RequestParser {

  import RequestParser._

  override def parse(request: String): Either[ParseError, Form] = {

    parser.decode[Form](request)(formDecoder).left.map(l => ParseError(l.getMessage))

  }

  private val formDecoder: Decoder[Form] = new Decoder[Form] {
    final def apply(c: HCursor): Decoder.Result[Form] = {
      for {
        username  <- c.downField("username").as[String]
        password  <- c.downField("password").as[String]
        firstName <- c.downField("firstName").as[String]
        lastName  <- c.downField("lastName").as[String]
        age       <- c.downField("age").as[Int]
      } yield Form(username, password, firstName, lastName, age)
    }
  }

}
