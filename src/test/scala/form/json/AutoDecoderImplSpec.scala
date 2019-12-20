package form.json

import org.scalatest._

class AutoDecoderImplSpec extends FlatSpec with Matchers {

  "AutoDecoder" should "decode to case class (1)" in {

    val json: JsValue = Json.obj(
      "name" -> Json.str("Bob"),
      "age"  -> Json.num(42)
    )

    implicit val personDecoder: Decoder[Person] = Json.autoDecoder[Person]

    val reuslt: Option[Person] = json.as[Person]
    val answer                 = Some(Person("Bob", 42))

    reuslt shouldEqual answer

  }

}
