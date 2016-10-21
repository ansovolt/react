package com.ansosoft.item

import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import pl.newicom.dddd.streams.ImplicitMaterializer
import akka.util.Timeout
import com.ansosoft.item.app.ReservationViewEndpoint
import com.typesafe.config.Config
import org.json4s.Formats
import pl.newicom.dddd.serialization.JsonSerHints._

import scala.concurrent.duration.FiniteDuration
import slick.driver.PostgresDriver


object HttpService {
  def props(interface: String, port: Int, askTimeout: FiniteDuration): Props =
    Props(new HttpService(interface, port)(askTimeout))
}

class HttpService(interface: String, port: Int)(implicit askTimeout: Timeout) extends Actor with ActorLogging
  with ItemReadFrontConfiguration with ImplicitMaterializer with Directives {

  import context.dispatcher

  implicit val formats: Formats = fromConfig(config)
  implicit val profile = PostgresDriver

  Http(context.system).bindAndHandle(route, interface, port)

  log.info(s"Listening on $interface:$port")

  override def receive = Actor.emptyBehavior
  override def config: Config = context.system.settings.config

  lazy val endpoints: ReservationViewEndpoint = ReservationViewEndpoint()

  private def route = (provide(viewStore) & pathPrefix("ecommerce" / "sales"))(endpoints)

}
