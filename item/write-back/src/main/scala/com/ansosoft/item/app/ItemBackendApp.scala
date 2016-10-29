package com.ansosoft.item.app

import _root_.akka.cluster.Cluster
import akka.kernel.Bootable
import com.ansosoft.app.ItemBackendConfiguration
import com.ansosoft.item.Reservation
import pl.newicom.dddd.cluster._
import pl.newicom.dddd.office.OfficeFactory.office

class ItemBackendApp extends Bootable with ItemBackendConfiguration {

  override def startup() = {
    Cluster(system).registerOnMemberUp {
      office[Reservation]
    }
  }

}