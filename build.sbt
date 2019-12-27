import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val commonSettings = Seq(
  scalacOptions ++= "-deprecation" :: "-feature" :: "-Xlint" :: Nil,
  scalacOptions in (Compile, console) ~= {_.filterNot(_ == "-Xlint")},
  scalafmtOnCompile := true,
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
)

lazy val root = (project in file("."))
  .settings(
    name := "scala-sandbox",
    commonSettings,
    libraryDependencies += scalaTest % Test,
    libraryDependencies += scalaMock % Test,
    libraryDependencies ++= cats,
    libraryDependencies ++= circe,
    libraryDependencies ++= akka,
    libraryDependencies += scalaz,
    libraryDependencies += scalaParser,
    libraryDependencies += scalaRefrect,
  )

lazy val infra = (project in file("infra"))
  .settings(
    name := "scala-sandbox-infra",
    commonSettings,
    libraryDependencies += mysql,
  )
  .dependsOn(root)

lazy val gui = (project in file("gui"))
  .settings(
    name := "scala-sandbox-gui",
    commonSettings,
    libraryDependencies += scalafx,
    fork in run := true,
  )
  .dependsOn(root)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
