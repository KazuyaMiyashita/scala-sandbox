package mycats.validateOptions

case class Name(value: String)

object Name {

  trait NameError {
    def validate(value: String): Either[NameError, Unit]
  }
  object NameError {
    object NameMustNotBeEmpty extends NameError {
      override def validate(value: String): Either[NameError, Unit] = {
        Either.cond(
          value.nonEmpty,
          (),
          NameMustNotBeEmpty
        )
      }
    }
    object NameMustBeAlphabetic extends NameError {
      override def validate(value: String): Either[NameError, Unit] = {
        Either.cond(
          value.matches("[a-zA-Z]+"),
          (),
          NameMustBeAlphabetic
        )
      }
    }
  }

  def fromValue(value: String): Either[NameError, Name] = {
    import NameError._
    for {
      _ <- NameMustNotBeEmpty.validate(value)
      _ <- NameMustBeAlphabetic.validate(value)
    } yield Name(value)
  }

}
