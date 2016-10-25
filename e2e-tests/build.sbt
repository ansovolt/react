import sbt._
import sbt.Keys._
import Vagrant._
import E2EConfig._

lazy val `e2e-tests` = (project in file("."))
  .settings(e2eTestingSettings ++ Vagrant.settings)
  .settings(
    scalacOptions += "-language:existentials",
    vagrantFile := baseDirectory.value / "Vagrantfile",
    vagrantContainersLogFile := target.value / "e2e-tests.log",
    libraryDependencies ++= Seq(
      "org.yaml" % "snakeyaml" % "1.14",
      "pl.newicom" %% "resttest" % "0.3.3"
    )
  )
  .configs(E2ETest)
  .dependsOn("item-contracts")

