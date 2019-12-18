import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
  lazy val cats = "org.typelevel" %% "cats-core" % "2.0.0"
  lazy val circe = Seq(
    "io.circe" %% "circe-core" % "0.12.3",
    "io.circe" %% "circe-generic" % "0.12.3",
    "io.circe" %% "circe-parser" % "0.12.3"
  )
  lazy val scalaz = "org.scalaz" %% "scalaz-core" % "7.2.29"
}
