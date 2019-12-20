package form.json

trait Converter[T] {
  def convert(js: JsValue): Option[T]
}

object Converter {

  implicit object StringConverter extends Converter[String] {
    override def convert(js: JsValue): Option[String] = js match {
      case JsString(value) => Some(value)
      case _               => None
    }
  }

  implicit object DoubleConverter extends Converter[Double] {
    override def convert(js: JsValue): Option[Double] = js match {
      case JsNumber(value) => Some(value)
      case _               => None
    }
  }

  implicit object BooleanConverter extends Converter[Boolean] {
    override def convert(js: JsValue): Option[Boolean] = js match {
      case JsBoolean(value) => Some(value)
      case _                => None
    }
  }

}
