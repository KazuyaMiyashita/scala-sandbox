package mycats.monadops

import cats.implicits._

object Monadops {

  val a: Either[Int, Int] = Right(1)
  val b: Either[Int, Int] = Left(2)
  val c: Either[Int, Int] = Right(3)

  val aaa = a >> b >> c

}
