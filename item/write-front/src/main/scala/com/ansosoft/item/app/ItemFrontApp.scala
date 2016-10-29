package com.ansosoft.item.app

import akka.actor._
import akka.kernel.Bootable
import com.ansosoft.app.ItemFrontConfiguration

class ItemFrontApp extends Bootable {

  override def systemName = "item-front"

  override def startup(): Unit = {
    system.actorOf(ItemFrontAppSupervisor.props, "item-front-supervisor")
  }

}

object ItemFrontAppSupervisor {
  def props = Props(new ItemFrontAppSupervisor)
}

class ItemFrontAppSupervisor extends Actor with ActorLogging with ItemFrontConfiguration {

  override val supervisorStrategy = SupervisorStrategy.stoppingStrategy

  context.watch(createHttpService())

  override def receive: Receive = {
    case Terminated(ref) =>
      log.warning("Shutting down, because {} has terminated!", ref.path)
      context.system.terminate()
  }

  protected def createHttpService(): ActorRef = {
    import httpService._
    context.actorOf(HttpService.props(interface, port, timeout), "http-service")
  }
}