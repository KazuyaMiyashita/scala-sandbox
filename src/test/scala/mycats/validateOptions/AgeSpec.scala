package mycats.validateOptions

import org.scalatest._

import mycats.validateOptions.Age.AgeError._

class AgeSpec extends FlatSpec with Matchers {

  "Age" should "be in 18 to 60" in {

    Age.fromValue(17) shouldEqual Left(OutOfRange)
    Age.fromValue(18) shouldEqual Right(Age(18))
    Age.fromValue(60) shouldEqual Right(Age(60))
    Age.fromValue(61) shouldEqual Left(OutOfRange)

  }

}
