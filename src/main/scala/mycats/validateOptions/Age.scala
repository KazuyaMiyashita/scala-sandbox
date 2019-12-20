package mycats.validateOptions

case class Age(value: Int)

object Age {

  trait AgeError {
    def validate(value: Int): Either[AgeError, Unit]
  }
  object AgeError {
    object OutOfRange extends AgeError {
      override def validate(value: Int): Either[AgeError, Unit] = {
        Either.cond(
          18 <= value && value <= 60,
          (),
          OutOfRange
        )
      }
    }
  }

  def fromValue(value: Int): Either[AgeError, Age] = {
    import AgeError._
    for {
      _ <- OutOfRange.validate(value)
    } yield Age(value)
  }

}
