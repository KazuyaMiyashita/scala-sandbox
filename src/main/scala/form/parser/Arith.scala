package form.parser

import scala.util.parsing.combinator._

/**
  * コップ33章 パーサーコンビネーターの例
  */
class Arith extends JavaTokenParsers {

  /*
  expr   ::= term {"+" term | "-" term}.
  term   ::= factor {"*" factor | "/" factor}.
  factor ::= floatingPointNumber | "(" expr ")".
   */
  def expr: Parser[Any]   = term ~ rep("+" ~ term | "-" ~ term)
  def term: Parser[Any]   = factor ~ rep("*" ~ factor | "/" ~ factor)
  def factor: Parser[Any] = floatingPointNumber | "(" ~ expr ~ ")"

}
