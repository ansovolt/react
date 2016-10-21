package com.ansosoft.item

import pl.newicom.dddd.view.sql.SqlViewStoreConfiguration
import pl.newicom.eventstore.EventStoreProvider

trait ItemReadBackendConfiguration extends SqlViewStoreConfiguration with EventStoreProvider
