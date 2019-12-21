package form.json

import language.experimental.macros

object Json {

  def str(value: String): JsValue    = JsString(value)
  def num(value: Double): JsValue    = JsNumber(value)
  def bool(value: Boolean): JsValue  = JsBoolean(value)
  def obj(value: (String, JsValue)*) = JsObject(value.toMap)
  def arr(value: JsValue*)           = JsArray(value.toVector)
  val nul                            = JsNull

  def parse(input: String): Either[String, JsValue] = JsonParser(input)

  def decode[T](js: JsValue)(implicit decoder: Decoder[T]): Option[T] = decoder.decode(js)
  def autoDecoder[T]: Decoder[T] = macro AutoDecoderImpl[T]

  def encode[T](value: T)(implicit encoder: Encoder[T]): JsValue = encoder.encode(value)

}
