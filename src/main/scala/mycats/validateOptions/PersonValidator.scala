package mycats.validateOptions

import cats.data.ValidatedNec
import cats.implicits._

import mycats.validateOptions.Name.NameError
import mycats.validateOptions.Age.AgeError

object PersonValidator {

  def validate(nameValue: String, ageValue: Int): Either[List[PersonValidatorError], Person] = {
    import PersonValidatorError._
    val v: ValidatedNec[PersonValidatorError, Person] = (
      Name.fromValue(nameValue).left.map(InvalidName).toValidatedNec,
      Age.fromValue(ageValue).left.map(InvalidAge).toValidatedNec
    ).mapN(Person)

    v.toEither.left.map(_.toList)

  }

  trait PersonValidatorError
  object PersonValidatorError {
    case class InvalidName(err: NameError) extends PersonValidatorError
    case class InvalidAge(err: AgeError)   extends PersonValidatorError
  }

}
