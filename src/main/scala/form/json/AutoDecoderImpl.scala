package form.json

import scala.reflect.macros.whitebox.Context

object AutoDecoderImpl {

  def apply[T: c.WeakTypeTag](c: Context): c.Expr[Decoder[T]] = {
    import c.universe._

    val tag = weakTypeTag[T]

    case class Field(name: String, tpe: String) {
      val option = """Option\[(.*)]""".r
      def expression: String = {
        def addThis(base: String, tpe: String) =
          if (tpe.contains(s"[${tag.tpe.toString}]")) base + "(this)" else base
        tpe match {
          case option(tp) =>
            addThis(s"""$name <- (obj \\ "$name").asOpt[$tp]""", tp)
          case _ =>
            addThis(s"""$name <- (obj \\ "$name").as[$tpe]""", tpe)
        }
      }
    }
    val fields: List[Field] = tag.tpe.typeSymbol.typeSignature.decls.toList.collect {
      case sym: TermSymbol if sym.isVal && sym.isCaseAccessor => {
        Field(sym.name.toString.trim, tq"${sym.typeSignature}".toString)
      }
    }

    val code: String =
      s"""
      |new Decoder[${tag.tpe.toString}] {
      |  override def decode(js: JsValue): Option[${tag.tpe.toString}] = js match {
      |    case obj: JsObject =>
      |      for {
      |        ${fields.map(_.expression).mkString("\n        ")}
      |      } yield ${tag.tpe.toString}(${fields.map(_.name).mkString(", ")})
      |    case _ => None
      |  }
      |}
      |""".stripMargin
    println(code)

    c.Expr[Decoder[T]](c.parse(code))

  }

}
