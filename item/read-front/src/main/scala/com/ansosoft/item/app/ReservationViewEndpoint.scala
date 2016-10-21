package com.ansosoft.item.app

case class ReservationViewEndpoint(implicit ec: ExecutionContext, profile: JdbcProfile, formats: Formats) extends ReadEndpoint {

  lazy val dao = new ReservationDao

  def route(viewStore: Database): Route = {
    path("reservation" / "all") {
      get {
        complete {
          viewStore.run {
            dao.all
          }
        }
      }
    } ~
    path("reservation" / Segment) { id =>
      get {
        onSuccess(viewStore.run(dao.byId(id))) {
          case Some(res) => complete(res)
          case None => complete(StatusCodes.NotFound -> "unknown reservation")
        }
      }
    }

  }

}
