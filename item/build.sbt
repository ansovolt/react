import Deps._
import com.typesafe.sbt.SbtMultiJvm
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm
import sbt.Keys._

lazy val item = (project in file(".")).aggregate( `item-read-front`,`item-read-back`,`item-write-back`,`item-write-front`)

lazy val `item-contracts` = (project in file("contracts"))
  .settings(
    libraryDependencies += AkkaDDD.messaging
  )

  
 
lazy val `item-write-back` = (project in file("write-back"))
  .settings(
    dockerExposedPorts := Seq(9101),
    javaOptions in Universal += "-DmainClass=com.ansosoft.item.app.ItemBackendApp",
    //multiNodeTestingSettings,
    libraryDependencies ++=
      Seq(AkkaDDD.core, AkkaDDD.test, AkkaDDD.eventStore, AkkaDDD.monitoring, AkkaDDD.scheduling)
  )
  .dependsOn(`item-contracts`, "commons")
  .configs(MultiJvm)
  .enablePlugins(ApplicationPlugin)
  .disablePlugins(EventStoreDockerPlugin,ViewStoreDockerPlugin)



lazy val `item-write-front` = (project in file("write-front"))
  .settings(
      dockerExposedPorts := Seq(9100),
      javaOptions in Universal ++= Seq("-DmainClass=com.ansosoft.item.app.ItemFrontApp"),
      libraryDependencies += AkkaDDD.writeFront
  )
  .dependsOn(`item-contracts`, "commons")
  .enablePlugins(HttpServerPlugin)
  .disablePlugins(EventStoreDockerPlugin,ViewStoreDockerPlugin)

 
  
lazy val `item-read-back` = (project in file("read-back"))
  .settings(
    javaOptions in Universal ++= Seq("-DmainClass=com.ansosoft.item.app.ItemViewUpdateApp"),
    libraryDependencies ++= AkkaDDD.viewUpdateSql ++ Seq(AkkaDDD.eventStore)
  )
  .dependsOn(`item-contracts`, "commons")
  .disablePlugins(EventStoreDockerPlugin,ViewStoreDockerPlugin)
  .enablePlugins(ApplicationPlugin)

  
lazy val `item-read-front` = (project in file("read-front"))
  .settings(
    javaOptions in Universal ++= Seq("-DmainClass=com.ansosoft.item.app.ItemReadFrontApp"),
    dockerExposedPorts := Seq(9110)
  )
  .dependsOn(`item-read-back` % "test->test;compile->compile", "commons")
  .enablePlugins(HttpServerPlugin)
  .disablePlugins(EventStoreDockerPlugin,ViewStoreDockerPlugin)

