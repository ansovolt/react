package com.ansosoft.item.view

import java.sql.Date

import pl.newicom.dddd.aggregate._
import com.ansosoft.item.ReservationStatus

/**
  * Created by admin on 10/21/16.
  */
case class ReservationView(
                            id: EntityId,
                            clientId: EntityId,
                            status: ReservationStatus,
                            createDate: Date)
