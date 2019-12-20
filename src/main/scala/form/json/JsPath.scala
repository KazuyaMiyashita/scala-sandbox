package form.json

case class JsPath(getOption: Option[JsValue]) {
  def \(key: String): JsPath                           = JsPath(getOption.map(_ \ key).flatMap(_.getOption))
  def \(key: Int): JsPath                              = JsPath(getOption.map(_ \ key).flatMap(_.getOption))
  final def as[T](implicit c: Converter[T]): Option[T] = getOption.flatMap(c.convert(_))
}
