package form.parser

import org.scalatest._

class ArithSpec extends FlatSpec with Matchers {

  val parser = new Arith

  "2 * (3 + 7)" should "parse successful" in {

    val input  = "2 * (3 + 7)"
    val result = parser.parseAll(parser.expr, input)

    result.successful shouldEqual true

  }

  "2 * (3 + 7))" should "parse unsuccessful" in {

    val input  = "2 * (3 + 7))"
    val result = parser.parseAll(parser.expr, input)

    result.successful shouldEqual false

  }

}
