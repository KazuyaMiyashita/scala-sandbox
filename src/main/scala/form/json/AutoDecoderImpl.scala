package form.json

import scala.reflect.macros.whitebox.Context
// import scala.language.experimental.macros

object AutoDecoderImpl {

  def apply[T](c: Context): c.Expr[Decoder[T]] = {
    import c.universe._

    c.Expr(q"""
    new Decoder[Person] {
      override def decode(js: JsValue): Option[Person] = None
    }
    """)
  }

}
