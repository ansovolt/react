package com.ansosoft.app

import akka.cluster.Cluster
import akka.kernel.Bootable
import pl.newicom.dddd.cluster._
import com.ansosoft.ClusterView
//import com.ansosoft.processes.OrderProcessManager
import pl.newicom.dddd.office.OfficeFactory._
import pl.newicom.dddd.process.ReceptorSupport.registerReceptor
import pl.newicom.dddd.scheduling.{DeadlinesReceptor, Scheduler}

class HeadquartersApp extends Bootable { //with HeadquartersConfiguration {

  override def startup() = {
    system.actorOf(ClusterView.props, ClusterView.Name)

    Cluster(system).registerOnMemberUp {

      //office[Scheduler]
      //registerReceptor(DeadlinesReceptor("global", "Headquarters"))

      //office(InvoicingOfficeId)
      //office(ReservationOfficeId)
      //office(ItemOfficeId)

      //office[OrderProcessManager]
    }
  }

}
