package form.json

import org.scalatest._

class JsValueSpec extends FlatSpec with Matchers {

  val template: JsValue = JsObject(
    Map(
      "name" -> JsString("WaterShip Down"),
      "location" -> JsObject(
        Map(
          "lat" -> JsNumber(51.235685),
          "lng" -> JsNumber(-1.309197)
        )
      ),
      "residents" -> JsArray(
        Vector(
          JsObject(
            Map(
              "name" -> JsString("Fiver"),
              "age"  -> JsNumber(4),
              "role" -> JsNull
            )
          ),
          JsObject(
            Map(
              "name" -> JsString("Bigwig"),
              "age"  -> JsNumber(6),
              "role" -> JsString("Owsla")
            )
          )
        )
      )
    )
  )

  "JsValue" should "build" in {

    template.isInstanceOf[JsValue] shouldEqual true

  }

  "JsValue" should "traverse (1)" in {

    val result: Option[JsValue] = (template \ "location" \ "lng").getOption
    val answer: Option[JsValue] = Some(JsNumber(-1.309197))

    result shouldEqual answer

  }

  "JsValue" should "traverse (2)" in {

    val result: Option[JsValue] = (template \ "residents" \ 1 \ "role").getOption
    val answer: Option[JsValue] = Some(JsString("Owsla"))

    result shouldEqual answer

  }

  "JsValue" should "traverse (3)" in {

    val result: Option[JsValue] = (template \ "residents" \ 0 \ "role").getOption
    val answer: Option[JsValue] = Some(JsNull)

    result shouldEqual answer

  }

  "JsValue" should "traverse (4)" in {

    val result: Option[JsValue] = (template \ "name" \ "wrongkey!").getOption
    val answer: Option[JsValue] = None

    result shouldEqual answer

  }

  "JsValue" should "convert string value" in {

    val json: JsValue          = JsString("string value")
    val reuslt: Option[String] = json.as[String]
    val answer                 = Some("string value")

    reuslt shouldEqual answer

  }

  "JsValue" should "convert double value" in {

    val json: JsValue          = JsNumber(42)
    val reuslt: Option[Double] = json.as[Double]
    val answer                 = Some(42)

    reuslt shouldEqual answer

  }

  "JsValue" should "convert boolean value" in {

    val json: JsValue           = JsBoolean(false)
    val reuslt: Option[Boolean] = json.as[Boolean]
    val answer                  = Some(false)

    reuslt shouldEqual answer

  }

  "JsValue" should "convert custom format" in {

    import scala.util.Try
    import java.time.LocalDateTime

    implicit object LocalDateTimeConverter extends Converter[LocalDateTime] {
      def ldtconvert(str: String): Option[LocalDateTime] = {
        Try(LocalDateTime.parse(str)).toOption
      }
      override def convert(js: JsValue): Option[LocalDateTime] = js match {
        case JsString(value) => ldtconvert(value)
        case _               => None
      }
    }

    val json: JsValue                 = JsString("2007-12-03T10:15:30")
    val reuslt: Option[LocalDateTime] = json.as[LocalDateTime]
    val answer                        = Some(LocalDateTime.of(2007, 12, 3, 10, 15, 30))

    reuslt shouldEqual answer

  }

  "JsPath" should "convert value" in {

    val reuslt: Option[String] = (template \ "name").as[String]
    val answer                 = Some("WaterShip Down")

    reuslt shouldEqual answer

  }

}
