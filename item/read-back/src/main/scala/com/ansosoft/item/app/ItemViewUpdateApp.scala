package com.ansosoft.item.app

import akka.actor._
import akka.kernel.Bootable
import com.typesafe.config.ConfigFactory
import com.ansosoft.item.ItemViewUpdateService
import slick.driver.{JdbcProfile, PostgresDriver}


class ItemViewUpdateApp extends Bootable {

  private val config = ConfigFactory.load()
  val system = ActorSystem("item-view-update", config)

  def startup() = {
    implicit val profile: JdbcProfile = PostgresDriver
    system.actorOf(Props(new ItemViewUpdateService(config)), "item-view-update-service")
  }

  def shutdown() = {
    system.terminate()
  }

}
