package com.ansosoft.item.app

import akka.actor._
import akka.kernel.Bootable
import com.ansosoft.item.ItemViewUpdateService
import slick.driver.{JdbcProfile, PostgresDriver}


class ItemViewUpdateApp extends Bootable {

  override def systemName = "item-view-update"

  def startup() = {
    implicit val profile: JdbcProfile = PostgresDriver
    system.actorOf(Props(new ItemViewUpdateService(config)), "item-view-update-service")
  }

}
