package form.json

import scala.reflect.macros.whitebox.Context

object AutoDecoderImpl {

  def apply[T: c.WeakTypeTag](c: Context): c.Expr[Decoder[T]] = {
    import c.universe._

    val tag = weakTypeTag[T]
    val tpe = tag.tpe

    case class Field(name: String, tpe: String) {
      val option = """Option\[(.*)]""".r
      def expression: String = {
        tpe match {
          case option(tp) =>
            s"""$name <- (obj \\ "$name").asOpt[$tp]"""
          case _ =>
            s"""$name <- (obj \\ "$name").as[$tpe]"""
        }
      }
    }
    val fields: List[Field] = tpe.typeSymbol.typeSignature.decls.toList.collect {
      case sym: TermSymbol if sym.isVal && sym.isCaseAccessor => {
        Field(sym.name.toString.trim, tq"${sym.typeSignature}".toString)
      }
    }

    val code: String =
      s"""
      |new Decoder[${tpe.toString}] {
      |  override def decode(js: JsValue): Option[${tpe.toString}] = js match {
      |    case obj: JsObject =>
      |      for {
      |        ${fields.map(_.expression).mkString("\n        ")}
      |      } yield ${tpe.toString}(${fields.map(_.name).mkString(", ")})
      |    case _ => None
      |  }
      |}
      |""".stripMargin
    println(code)

    c.Expr[Decoder[T]](c.parse(code))

  }

}
