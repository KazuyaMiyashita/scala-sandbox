package mycats.registrationExample

trait FormValidator {

  def validateForm(
      username: String,
      password: String,
      firstName: String,
      lastName: String,
      age: Int
  ): Either[List[DomainValidation], RegistrationData]

}
