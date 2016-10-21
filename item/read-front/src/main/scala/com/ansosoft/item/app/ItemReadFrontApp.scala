package com.ansosoft.item.app

import com.ansosoft.item.ItemReadFrontConfiguration
import com.ansosoft.item.HttpService

class ItemReadFrontApp extends Bootable {
   val config = ConfigFactory.load()
   val system = ActorSystem("sales-read-front", config)

   def startup() = {
     new ItemReadFrontConfiguration {
       override def config: Config = ItemReadFrontApp.this.config
       system.actorOf(HttpService.props(interface, port, askTimeout), "http-service")
     }
   }

   def shutdown() = {
     system.terminate()
   }
 }