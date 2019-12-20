package mycats.validateOptions

import org.scalatest._

import mycats.validateOptions.Name.NameError
import mycats.validateOptions.Name.NameError._

class NameSpec extends FlatSpec with Matchers {

  "Name(Foo)" should "be created" in {

    val errOrName: Either[NameError, Name] = Name.fromValue("Foo")

    errOrName shouldEqual Right(Name("Foo"))

  }

  "Name" should "not be empty" in {

    val errOrName: Either[NameError, Name] = Name.fromValue("")

    errOrName shouldEqual Left(NameMustNotBeEmpty)

  }

  "Name" should "be alphabetic" in {

    val errOrName: Either[NameError, Name] = Name.fromValue("なまえ")

    errOrName shouldEqual Left(NameMustBeAlphabetic)

  }

}
