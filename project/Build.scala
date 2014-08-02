import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "yagince"
  val buildVersion      = "0.0.1"
  val buildScalaVersion = "2.11.1"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object Resolvers {
  val typeSafe = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  val sprayIO = "spray" at "http://repo.spray.io/"
  val otherResolvers = Seq(typeSafe, sprayIO)
}

object Dependencies {
  val akkaCore = "com.typesafe.akka" %% "akka-actor" % "2.3.4"
  val json4s = "org.json4s" %% "json4s-native" % "3.2.10"
  val sprayJson = "io.spray" %%  "spray-json" % "1.2.6"
}

object AkkaPracticeBuild extends Build {
  import Resolvers._
  import Dependencies._
  import BuildSettings._

  val dependencies = Seq (
    akkaCore,
    json4s,
    sprayJson
  )
  
  val project = Project (
    "akka-practice",
    file("."),
    settings =
      buildSettings ++ Seq (
        resolvers := otherResolvers,
        libraryDependencies ++= dependencies
      )
  )
}
