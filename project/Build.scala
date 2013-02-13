import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "yagince"
  val buildVersion      = "0.0.1"
  val buildScalaVersion = "2.10.0"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object Resolvers {
  val typeSafe = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  val otherResolvers = Seq(typeSafe)
}

object Dependencies {
  val akkaCore = "com.typesafe.akka" %% "akka-actor" % "2.1.0"
}

object AkkaPracticeBuild extends Build {
  import Resolvers._
  import Dependencies._
  import BuildSettings._

  val dependencies = Seq (
    akkaCore
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
