name := """analytics"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, GatlingPlugin)
  .settings(scalaSource in Gatling := (sourceDirectory in Test).value)

scalaVersion := "2.12.8"


libraryDependencies += ws
libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.2.1" % Test
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "3.2.1" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
