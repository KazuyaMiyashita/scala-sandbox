package form.json

object Json {

  def str(value: String): JsValue    = JsString(value)
  def num(value: Double): JsValue    = JsNumber(value)
  def bool(value: Boolean): JsValue  = JsBoolean(value)
  def obj(value: (String, JsValue)*) = JsObject(value.toMap)
  def arr(value: JsValue*)           = JsArray(value.toVector)
  val nul                            = JsNull

  def parse(input: String): Either[String, JsValue] = JsonParser(input)

}
