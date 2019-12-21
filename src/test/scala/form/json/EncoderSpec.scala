package form.json

import org.scalatest._

class EncoderSpec extends FlatSpec with Matchers {

  "Encoder" should "encode String" in {

    val value           = "Foo"
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.str("Foo")

    result shouldEqual answer

  }

  "Encoder" should "encode Double" in {

    val value           = 3.14
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.num(3.14)

    result shouldEqual answer

  }

  "Encoder" should "encode Int" in {

    val value           = 42
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.num(42)

    result shouldEqual answer

  }

  "Encoder" should "encode Boolean" in {

    val value           = false
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.bool(false)

    result shouldEqual answer

  }

  "Encoder" should "encode Iterable" in {

    val value           = Iterable(1, 1, 2, 3, 5)
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.arr(
      Json.num(1),
      Json.num(1),
      Json.num(2),
      Json.num(3),
      Json.num(5)
    )

    result shouldEqual answer

  }

  "Encoder" should "encode List" in {

    val value           = List(1, 1, 2, 3, 5)
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.arr(
      Json.num(1),
      Json.num(1),
      Json.num(2),
      Json.num(3),
      Json.num(5)
    )

    result shouldEqual answer

  }

  "Encoder" should "encode Nil" in {

    val value           = Nil
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.arr()

    result shouldEqual answer

  }

  "Encoder" should "encode Option (Some) 1" in {

    val value: Option[Int] = Some(42)
    val result: JsValue    = Json.encode(value)
    val answer: JsValue    = Json.num(42)

    result shouldEqual answer

  }

  "Encoder" should "encode Option (Some) 2" in {

    val value           = Some(42) // Some[Int]
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.num(42)

    result shouldEqual answer

  }

  "Encoder" should "encode Option (None) 1" in {

    val value: Option[Int] = None
    val result: JsValue    = Json.encode(value)
    val answer: JsValue    = Json.nul

    result shouldEqual answer

  }

  "Encoder" should "encode Option (None) 2" in {

    val value           = None // None.type
    val result: JsValue = Json.encode(value)
    val answer: JsValue = Json.nul

    result shouldEqual answer

  }

}
