import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

scalacOptions ++= "-deprecation" :: "-feature" :: "-Xlint" :: Nil
scalacOptions in (Compile, console) ~= {_.filterNot(_ == "-Xlint")}

lazy val root = (project in file("."))
  .settings(
    name := "scala-sandbox",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += scalaMock % Test,
    libraryDependencies += cats,
    libraryDependencies ++= circe,
    libraryDependencies ++= akka,
    libraryDependencies += scalaz,
    scalafmtOnCompile := true,
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
