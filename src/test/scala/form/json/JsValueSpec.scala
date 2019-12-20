package form.json

import org.scalatest._

class JsValueSpec extends FlatSpec with Matchers {

  "JsValue" should "build" in {

    val jsValue: JsValue = JsObject(
      Map(
        "name" -> JsString("WaterShip Down"),
        "location" -> JsObject(
          Map(
            "lat" -> JsNumber(51.235685),
            "lng" -> JsNumber(-1.309197)
          )
        ),
        "residents" -> JsArray(
          List(
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

    jsValue.isInstanceOf[JsValue] shouldEqual true

  }

}
