package com.ansosoft.item.view

import java.sql.Date

import com.ansosoft.item.ReservationStatus.ReservationStatus
import pl.newicom.dddd.aggregate._


/**
  * Created by admin on 10/21/16.
  */
case class ReservationView(
                            id: EntityId,
                            clientId: EntityId,
                            status: ReservationStatus,
                            createDate: Date)
