package form.json

import scala.reflect.macros.whitebox.Context

object AutoEncoderImpl {

  def apply[T: c.WeakTypeTag](c: Context): c.Expr[Encoder[T]] = {
    import c.universe._

    val tag = weakTypeTag[T]

    case class Field(name: String, tpe: String) {
      def expression: String = {
        s""""$name" -> Json.encode(value.$name)"""
      }
    }
    val fields: List[Field] = tag.tpe.typeSymbol.typeSignature.decls.toList.collect {
      case sym: TermSymbol if sym.isVal && sym.isCaseAccessor => {
        Field(sym.name.toString.trim, tq"${sym.typeSignature}".toString)
      }
    }

    val code: String =
      s"""
      |implicit object AnonAutoEncoder extends Encoder[${tag.tpe.toString}] {
      |  override def encode(value: ${tag.tpe.toString}): JsValue = Json.obj(
      |    ${fields.map(_.expression).mkString(",\n    ")}
      |  )
      |}
      |AnonAutoEncoder
      |""".stripMargin
    // println(code) // DEBUG

    c.Expr[Encoder[T]](c.parse(code))
  }

}
