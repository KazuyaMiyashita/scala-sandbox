package mycats.validateOptions

import org.scalatest._

import mycats.validateOptions.PersonValidator.PersonValidatorError
import mycats.validateOptions.PersonValidator.PersonValidatorError._
import mycats.validateOptions.Name.NameError._
import mycats.validateOptions.Age.AgeError._

class PersonValidatorSpec extends FlatSpec with Matchers {

  "PersonValidator" should "be right" in {

    val nameValue = "Foo"
    val ageValue  = 24

    val errsOrPerson: Either[List[PersonValidatorError], Person] = PersonValidator.validate(nameValue, ageValue)

    errsOrPerson shouldEqual Right(Person(Name(nameValue), Age(ageValue)))

  }

  "PersonValidator" should "be invalid name" in {

    val nameValue = "Foo***"
    val ageValue  = 24

    val errsOrPerson: Either[List[PersonValidatorError], Person] = PersonValidator.validate(nameValue, ageValue)

    errsOrPerson shouldEqual Left(List(InvalidName(NameMustBeAlphabetic)))

  }

  "PersonValidator" should "be invalid age" in {

    val nameValue = "Foo"
    val ageValue  = 10

    val errsOrPerson: Either[List[PersonValidatorError], Person] = PersonValidator.validate(nameValue, ageValue)

    errsOrPerson shouldEqual Left(List(InvalidAge(OutOfRange)))

  }

  "PersonValidator" should "be invalid List" in {

    val nameValue = "Foo***"
    val ageValue  = 10

    val errsOrPerson: Either[List[PersonValidatorError], Person] = PersonValidator.validate(nameValue, ageValue)

    errsOrPerson shouldEqual Left(List(InvalidName(NameMustBeAlphabetic), InvalidAge(OutOfRange)))

  }

}
