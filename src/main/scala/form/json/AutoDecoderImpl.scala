package form.json

import scala.reflect.macros.whitebox.Context
// import scala.language.experimental.macros

object AutoDecoderImpl {

  def apply[T: c.WeakTypeTag](c: Context): c.Expr[Decoder[T]] = {
    import c.universe._

    val a   = weakTypeTag[T]
    val tpe = a.tpe

    c.Expr[Decoder[T]](q"""
    new Decoder[$tpe] {
      override def decode(js: JsValue): Option[$tpe] = None
    }
    """)
  }

}
