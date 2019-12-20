package form.json

trait JsValue
case class JsString(value: String)               extends JsValue
case class JsNumber(value: Double)               extends JsValue
case class JsBoolean(value: Boolean)             extends JsValue
case class JsObject(value: Map[String, JsValue]) extends JsValue
case class JsArray(value: List[JsValue])         extends JsValue
case object JsNull                               extends JsValue
