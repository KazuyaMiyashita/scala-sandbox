package form.json

import org.scalatest._

class DecoderSpec extends FlatSpec with Matchers {

  "Decoder" should "decode string value" in {

    val json: JsValue          = JsString("string value")
    val reuslt: Option[String] = json.as[String]
    val answer                 = Some("string value")

    reuslt shouldEqual answer

  }

  "Decoder" should "decode double value" in {

    val json: JsValue          = JsNumber(42)
    val reuslt: Option[Double] = json.as[Double]
    val answer                 = Some(42)

    reuslt shouldEqual answer

  }

  "Decoder" should "decode boolean value" in {

    val json: JsValue           = JsBoolean(false)
    val reuslt: Option[Boolean] = json.as[Boolean]
    val answer                  = Some(false)

    reuslt shouldEqual answer

  }

  "Custom Decoder" should "decode custom format" in {

    import scala.util.Try
    import java.time.LocalDateTime

    implicit object LocalDateTimeConverter extends Decoder[LocalDateTime] {
      def ldtconvert(str: String): Option[LocalDateTime] = {
        Try(LocalDateTime.parse(str)).toOption
      }
      override def decode(js: JsValue): Option[LocalDateTime] = js match {
        case JsString(value) => ldtconvert(value)
        case _               => None
      }
    }

    val json: JsValue                 = JsString("2007-12-03T10:15:30")
    val reuslt: Option[LocalDateTime] = json.as[LocalDateTime]
    val answer                        = Some(LocalDateTime.of(2007, 12, 3, 10, 15, 30))

    reuslt shouldEqual answer

  }

}
