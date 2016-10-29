import Deps._
import sbt.Keys._

sourcesInBase in ThisBuild := false

lazy val persistence = (project in file(".")).aggregate( `event-store`,`view-store`)

lazy val `event-store` = (project in file("event-store"))
  .settings(
    javaOptions in Universal ++= Seq("-DmainClass=com.ansosoft.item.app.ItemViewUpdateApp"),
    libraryDependencies ++= AkkaDDD.viewUpdateSql ++ Seq(AkkaDDD.eventStore)
  )
  .enablePlugins(EventStoreDockerPlugin)
  .disablePlugins(CommonDockerSettingsPlugin,ViewStoreDockerPlugin)


  
lazy val `view-store` = (project in file("view-store"))
  .settings(
    javaOptions in Universal ++= Seq("-DmainClass=com.ansosoft.item.app.ItemReadFrontApp")
    //dockerExposedPorts := Seq(9110)
  )
  .enablePlugins(ViewStoreDockerPlugin)
  .disablePlugins(CommonDockerSettingsPlugin,EventStoreDockerPlugin)

