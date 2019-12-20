package form.json

import scala.util.parsing.combinator._

class JsonParser extends JavaTokenParsers {

  def apply(input: String): Either[String, JsValue] = parseAll(value, input) match {
    case Success(result, next) => Right(result)
    case Failure(msg, next)    => Left(msg)
    case Error(msg, next)      => Left(msg)
  }

  def value: Parser[JsValue] = (
    obj |
      arr |
      dequotedStringLiteral ^^ {
        case string => JsString(string)
      } |
      floatingPointNumber ^^ {
        case number => JsNumber(number.toDouble)
      } |
      "null" ^^ {
        case _ => JsNull
      } |
      "true" ^^ {
        case _ => JsBoolean(true)
      } |
      "false" ^^ {
        case _ => JsBoolean(false)
      }
  )
  def obj: Parser[JsObject] = "{" ~ repsep(member, ",") ~ "}" ^^ {
    case (_ ~ members ~ _) => JsObject(members.toMap)
  }
  def arr: Parser[JsArray] = "[" ~ repsep(value, ",") ~ "]" ^^ {
    case (_ ~ values ~ _) => JsArray(values.to(Vector))
  }
  def member: Parser[(String, JsValue)] = (dequotedStringLiteral ~ ":" ~ value) ^^ {
    case (string ~ _ ~ value) => (string, value)
  }

  def dequotedStringLiteral: Parser[String] = stringLiteral ^^ { str =>
    str.substring(1, str.length - 1)
  }

}
