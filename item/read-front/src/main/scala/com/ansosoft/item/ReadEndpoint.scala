package com.ansosoft.item

abstract class ReadEndpoint(implicit formats: Formats) extends Endpoint[JdbcBackend#DatabaseDef] {
  type Database = JdbcBackend#DatabaseDef
}
