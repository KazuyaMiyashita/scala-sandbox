package form.json

trait JsValue {
  def \(key: String): JsPath
  def \(key: Int): JsPath
}
case class JsString(value: String) extends JsValue {
  override def \(key: String): JsPath = JsPath(None)
  override def \(key: Int): JsPath    = JsPath(None)
}
case class JsNumber(value: Double) extends JsValue {
  override def \(key: String): JsPath = JsPath(None)
  override def \(key: Int): JsPath    = JsPath(None)
}
case class JsBoolean(value: Boolean) extends JsValue {
  override def \(key: String): JsPath = JsPath(None)
  override def \(key: Int): JsPath    = JsPath(None)
}
case class JsObject(value: Map[String, JsValue]) extends JsValue {
  override def \(key: String): JsPath = JsPath(value.get(key))
  override def \(key: Int): JsPath    = JsPath(None)
}
case class JsArray(value: Vector[JsValue]) extends JsValue {
  override def \(key: String): JsPath = JsPath(None)
  override def \(key: Int): JsPath    = JsPath(value.lift(key))
}
case object JsNull extends JsValue {
  override def \(key: String): JsPath = JsPath(None)
  override def \(key: Int): JsPath    = JsPath(None)
}
