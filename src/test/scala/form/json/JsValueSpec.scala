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

}
