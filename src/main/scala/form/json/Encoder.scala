package form.json

trait Encoder[T] {
  def encode(value: T): JsValue
}

object Encoder {

  implicit object StringEncoder extends Encoder[String] {
    override def encode(value: String): JsValue = JsString(value)
  }

  implicit object DoubleEncoder extends Encoder[Double] {
    override def encode(value: Double): JsValue = JsNumber(value)
  }

  implicit object IntEncoder extends Encoder[Int] {
    override def encode(value: Int): JsValue = JsNumber(value)
  }

  implicit object BooleanEncoder extends Encoder[Boolean] {
    override def encode(value: Boolean): JsValue = JsBoolean(value)
  }

  implicit def iterableEncoder[U: Encoder] = new Encoder[Iterable[U]] {
    override def encode(value: Iterable[U]): JsValue = JsArray(value.map(Json.encode(_)).toVector)
  }

  implicit def optionEncoder[U: Encoder] = new Encoder[Option[U]] {
    override def encode(value: Option[U]): JsValue = value match {
      case Some(v) => Json.encode(v)
      case None    => JsNull
    }
  }

}
