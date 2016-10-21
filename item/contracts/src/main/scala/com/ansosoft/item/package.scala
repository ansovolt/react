package com.ansosoft

import pl.newicom.dddd.office.RemoteOfficeId

package object item {

  implicit object ReservationOfficeId extends RemoteOfficeId(
    id           = "Reservation",
    department   = "Item",
    messageClass = classOf[item.Command]
  )



}
