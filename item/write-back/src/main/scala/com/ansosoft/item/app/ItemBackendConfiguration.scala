package com.ansosoft.app

import akka.actor._
import akka.kernel.Bootable
import com.ansosoft.item.Reservation
import pl.newicom.dddd.actor.PassivationConfig
import pl.newicom.dddd.aggregate._
import pl.newicom.dddd.eventhandling.NoPublishing
import pl.newicom.dddd.monitoring.AggregateRootMonitoring
import pl.newicom.dddd.cluster._

trait ItemBackendConfiguration {
  this: Bootable =>

  implicit object ReservationARFactory extends AggregateRootActorFactory[Reservation] {
    override def props(pc: PassivationConfig) = Props(new Reservation(pc) with NoPublishing with AggregateRootMonitoring)
  }

}
