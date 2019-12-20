package form.json

import org.scalatest._

class JsonParserSpec extends FunSuite with Matchers {

  test("parse") {

    val parser = new JsonParser

    val input = """
    |{
    |  "name" : "Watership Down",
    |  "location" : {
    |    "lat" : 51.235685,
    |    "lng" : -1.309197
    |  },
    |  "residents" : [ {
    |    "name" : "Fiver",
    |    "age" : 4,
    |    "role" : null
    |  }, {
    |    "name" : "Bigwig",
    |    "age" : 6,
    |    "role" : "Owsla"
    |  } ]
    |}
    |""".stripMargin

    val result: Either[String, JsValue] = parser(input)
    val answer: Either[String, JsValue] = Right(
      JsObject(
        Map(
          "name" -> JsString("Watership Down"),
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
    )

    result shouldEqual answer

  }

}
