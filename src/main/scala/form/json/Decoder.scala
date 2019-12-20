package form.json

trait Decoder[T] {
  def decode(js: JsValue): Option[T]
}

object Decoder {

  implicit object StringDecoder extends Decoder[String] {
    override def decode(js: JsValue): Option[String] = js match {
      case JsString(value) => Some(value)
      case _               => None
    }
  }

  implicit object DoubleDecoder extends Decoder[Double] {
    override def decode(js: JsValue): Option[Double] = js match {
      case JsNumber(value) => Some(value)
      case _               => None
    }
  }

  implicit object BooleanDecoder extends Decoder[Boolean] {
    override def decode(js: JsValue): Option[Boolean] = js match {
      case JsBoolean(value) => Some(value)
      case _                => None
    }
  }

}
