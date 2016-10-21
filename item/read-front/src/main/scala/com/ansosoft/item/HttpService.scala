package com.ansosoft.item

object HttpService {
  def props(interface: String, port: Int, askTimeout: FiniteDuration): Props =
    Props(new HttpService(interface, port)(askTimeout))
}

class HttpService(interface: String, port: Int)(implicit askTimeout: Timeout) extends Actor with ActorLogging
  with SalesReadFrontConfiguration with ImplicitMaterializer with Directives {

  implicit val formats: Formats = fromConfig(config)
  implicit val profile = PostgresDriver

  Http(context.system).bindAndHandle(route, interface, port)

  log.info(s"Listening on $interface:$port")

  override def receive = Actor.emptyBehavior
  override def config: Config = context.system.settings.config

  lazy val endpoints: ReservationViewEndpoint = ReservationViewEndpoint()

  private def route = (provide(viewStore) & pathPrefix("ecommerce" / "sales"))(endpoints)

}
