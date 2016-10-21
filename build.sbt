import sbt.Keys._


organization in ThisBuild := "pl.newicom"

version in ThisBuild := "0.3.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.7"

scalacOptions in ThisBuild := Seq("-encoding", "utf8", "-feature", "-language:postfixOps", "-language:implicitConversions"/*, "-Xlog-implicits"*/)

sourcesInBase in ThisBuild := false

lazy val root = project.settings(
  aggregate in update := false
).aggregate(item)

lazy val commons = project

lazy val item = project.dependsOn(commons)


// Rebuilds and restarts current application (or whole system if called from root project)
addCommandAlias("redeploy", ";clean;docker:stage;restart")
addCommandAlias("redeployQuick", ";docker:stage;restart")

// redeployQuick aliases per application
addCommandAlias("rsrf", ";project item-read-front;docker:stage;restart")

