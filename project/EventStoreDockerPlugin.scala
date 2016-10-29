import com.typesafe.sbt.packager.docker.{Cmd, DockerKeys, DockerPlugin}
import com.typesafe.sbt.packager.archetypes.{AshScriptPlugin, JavaAppPackaging}
import sbt._
import com.typesafe.sbt.packager.docker._

object EventStoreDockerPlugin extends AutoPlugin with DockerKeys {
  override def trigger = allRequirements
  override def requires = DockerPlugin && AshScriptPlugin
  //override def requires = CommonSettingsPlugin && JavaAppPackaging && AshScriptPlugin
  
  override lazy val projectSettings = Seq(
   
	  dockerCommands := Seq(
		  Cmd("FROM", "ruudud/eventstore:3.4.0")
	  )
 
  )
}