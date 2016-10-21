package com.ansosoft.item.app


import akka.kernel.Bootable
import com.ansosoft.item.{ItemReadFrontConfiguration,HttpService}
import com.typesafe.config.Config

class ItemReadFrontApp extends Bootable {

  override def systemName = "item-read-front"

  def startup() = {
    new ItemReadFrontConfiguration {
      override def config: Config = ItemReadFrontApp.this.config
      import httpService._
      system.actorOf(HttpService.props(interface, port, askTimeout), "http-service")
    }
  }

}