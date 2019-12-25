import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
  lazy val scalaMock = "org.scalamock" %% "scalamock" % "4.4.0"
  lazy val cats = "org.typelevel" %% "cats-core" % "2.0.0"
  lazy val circe = Seq(
    "io.circe" %% "circe-core" % "0.12.3",
    "io.circe" %% "circe-generic" % "0.12.3",
    "io.circe" %% "circe-parser" % "0.12.3"
  )
  lazy val akka = Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.6.1"
  )
  lazy val scalaz = "org.scalaz" %% "scalaz-core" % "7.2.29"
  lazy val scalaParser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
  lazy val scalaRefrect = "org.scala-lang" % "scala-reflect" % "2.13.1"

  lazy val mysql = "mysql" % "mysql-connector-java" % "8.0.18"
}
