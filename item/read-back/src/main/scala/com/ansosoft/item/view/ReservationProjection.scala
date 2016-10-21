package com.ansosoft.item.view

import java.sql.Date

import com.ansosoft.item.ReservationStatus._
import com.ansosoft.item._
import org.joda.time.DateTime.now
import pl.newicom.dddd.messaging.event.OfficeEventMessage
import pl.newicom.dddd.view.sql.Projection
import pl.newicom.dddd.view.sql.Projection.ProjectionAction
import slick.dbio.DBIOAction
import slick.dbio.Effect.Write

import scala.concurrent.ExecutionContext

/**
  * Created by admin on 10/21/16.
  */
class ReservationProjection(dao: ReservationDao)(implicit ec: ExecutionContext) extends Projection {

  override def consume(eventMessage: OfficeEventMessage): ProjectionAction[Write] = {
    eventMessage.event match {

      case ReservationCreated(id, clientId) =>
        val newView = ReservationView(id, clientId, Opened, new Date(now().getMillis))
        dao.createOrUpdate(newView)

      case ReservationConfirmed(id, clientId, _) =>
        dao.updateStatus(id, Confirmed)

      case ReservationCanceled(id) =>
        dao.updateStatus(id, Canceled)

      case ReservationClosed(id) =>
        dao.updateStatus(id, Closed)

      case ProductReserved(id, product, quantity) =>
        // TODO handle
        DBIOAction.successful(())
    }
  }
}