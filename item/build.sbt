import Deps._
import com.typesafe.sbt.SbtMultiJvm
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm
import sbt.Keys._

lazy val item = (project in file(".")).aggregate( `item-read-front`,`item-read-back`)

//lazy val `sales-contracts` = (project in file("contracts"))
//  .settings(
//    libraryDependencies += AkkaDDD.messaging
//  )
//
//
//lazy val `sales-write-back` = (project in file("wriΩe-back"))
//  .settings(
//    dockerExposedPorts := Seq(9101),
//    javaOptions in Universal += "-DmainClass=ecommerce.sales.app.SalesBackendApp",
//    multiNodeTestingSettings,
//    libraryDependencies ++=
//      Seq(AkkaDDD.core, AkkaDDD.test, AkkaDDD.eventStore, AkkaDDD.monitoring)
//  )
//  .dependsOn(`sales-contracts`, "invoicing-contracts", "commons")
//  .configs(MultiJvm)
//  .enablePlugins(ApplicationPlugin)
//
//
//
//lazy val `sales-write-front` = (project in file("write-front"))
//  .settings(
//    dockerExposedPorts := Seq(9100),
//    javaOptions in Universal ++= Seq("-DmainClass=ecommerce.sales.app.SalesFrontApp"),
//    libraryDependencies += AkkaDDD.writeFront
//  )
//  .dependsOn(`sales-contracts`, "commons")
//  .enablePlugins(HttpServerPlugin)
//
//

lazy val `item-read-back` = (project in file("read-back"))
  .settings(
    javaOptions in Universal ++= Seq("-DmainClass=com.ansosoft.item.app.ItemViewUpdateApp"),
    libraryDependencies ++= AkkaDDD.viewUpdateSql ++ Seq(AkkaDDD.eventStore)
  )
  .dependsOn(`item-contracts`, "commons")
  .enablePlugins(ApplicationPlugin)



lazy val `item-read-front` = (project in file("read-front"))
  .settings(
    javaOptions in Universal ++= Seq("-DmainClass=com.ansosoft.item.app.ItemReadFrontApp"),
    dockerExposedPorts := Seq(9110)
  )
  .dependsOn(`item-read-back` % "test->test;compile->compile", "commons")
  .enablePlugins(HttpServerPlugin)


//
//lazy val multiNodeTestingSettings: Seq[Setting[_]] = SbtMultiJvm.multiJvmSettings ++ Seq(
//  // make sure that MultiJvm test are compiled by the default test compilation
//  compile in MultiJvm <<= (compile in MultiJvm) triggeredBy (compile in Test),
//  // disable parallel tests
//  parallelExecution in Test := false,
//  // make sure that MultiJvm tests are executed by the default test target,
//  // and combine the results from ordinary test and multi-jvm tests
//  executeTests in Test <<= (executeTests in Test, executeTests in MultiJvm) map {
//    case (testResults, multiNodeResults)  =>
//      val overall =
//        if (testResults.overall.id < multiNodeResults.overall.id)
//          multiNodeResults.overall
//        else
//          testResults.overall
//      Tests.Output(overall,
//        testResults.events ++ multiNodeResults.events,
//        testResults.summaries ++ multiNodeResults.summaries)
//  },
//  libraryDependencies ++= Seq(
//    Akka.multiNodeTestkit
//  )
//)
