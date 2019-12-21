package form.json

import org.scalatest._

class AutoDecoderImplSpec extends FlatSpec with Matchers {

  "AutoDecoder" should "decode to case class (1)" in {

    case class Person(
        name: String,
        age: Int
    )

    val json: JsValue = Json.obj(
      "name" -> Json.str("Bob"),
      "age"  -> Json.num(42)
    )

    implicit val personDecoder: Decoder[Person] = Json.autoDecoder[Person]

    val reuslt: Option[Person] = json.as[Person]
    val answer                 = Some(Person("Bob", 42))

    reuslt shouldEqual answer

  }

  "AutoDecoder" should "decode to case class (2)" in {

    case class Person(
        name: String,
        age: Int,
        friend: Option[Int]
    )

    val json: JsValue = Json.obj(
      "name"   -> Json.str("Bob"),
      "age"    -> Json.num(42),
      "friend" -> Json.nul
    )

    implicit val personDecoder: Decoder[Person] = Json.autoDecoder[Person]

    val reuslt: Option[Person] = json.as[Person]
    val answer                 = Some(Person("Bob", 42, None))

    reuslt shouldEqual answer

  }

  "AutoDecoder" should "decode to case class (3)" in {

    case class Person(
        name: String,
        age: Int,
        friend: Option[Person] // ここで再帰的になるとコンパイルできない
    )

    val json: JsValue = Json.obj(
      "name"   -> Json.str("Bob"),
      "age"    -> Json.num(42),
      "friend" -> Json.nul
    )

    implicit val personDecoder: Decoder[Person] = Json.autoDecoder[Person]

    val reuslt: Option[Person] = json.as[Person]
    val answer                 = Some(Person("Bob", 42, None))

    reuslt shouldEqual answer

  }

}
