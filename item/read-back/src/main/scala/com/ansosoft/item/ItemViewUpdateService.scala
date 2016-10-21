package com.ansosoft.item

import com.typesafe.config.Config
import com.ansosoft.item.view.{ReservationDao, ReservationProjection}
import pl.newicom.dddd.view.sql.{SqlViewUpdateConfig, SqlViewUpdateService}
import pl.newicom.eventstore.EventSourceProvider
import slick.dbio.DBIO
import slick.driver.JdbcProfile

class ItemViewUpdateService(override val config: Config)(override implicit val profile: JdbcProfile)
  extends SqlViewUpdateService with ItemReadBackendConfiguration with EventSourceProvider {

  lazy val resevationDao = new ReservationDao

  override def vuConfigs: Seq[SqlViewUpdateConfig] = {
    List(
      SqlViewUpdateConfig("item-reservations", ReservationOfficeId, new ReservationProjection(resevationDao))
    )
  }

  override def viewUpdateInitAction: DBIO[Unit] = {
      super.viewUpdateInitAction >>
        resevationDao.ensureSchemaCreated
  }
}