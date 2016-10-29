import com.typesafe.sbt.packager.docker.{Cmd, DockerKeys, DockerPlugin}
import com.typesafe.sbt.packager.archetypes.{AshScriptPlugin, JavaAppPackaging}
import sbt._
import com.typesafe.sbt.packager.docker._

object ViewStoreDockerPlugin extends AutoPlugin with DockerKeys {
  override def trigger = allRequirements
  override def requires = DockerPlugin && AshScriptPlugin 

 
  
  override lazy val projectSettings = Seq(
  
  
	  dockerCommands := Seq(
		  Cmd("FROM", "postgres")
	  )
  
  
  /*
      dockerBaseImage := "postgres",
	  dockerExposedPorts := Seq(5432),
	  dockerEntrypoint := Seq(),
	  dockerCommands := dockerCommands.value.filterNot {
		  //case ExecCmd("ENTRYPOINT", args @ _*) => true
		  //case ExecCmd("RUN", args @ _*) => true
		  //case ExecCmd("CMD", args @ _*) => true
		  // don't filter the rest; don't filter out anything that doesn't match a pattern
		  case cmd                       => false
		}
	  
	  
	  //maintainer in Docker := "Andre",
	  //dockerExposedVolumes in Docker,
	  //dockerEntrypoint in Docker,
	  //dockerExposedVolumes := Seq("/opt/docker/logs")
	  //dockerCommands := Seq()
*/
        
  )
}