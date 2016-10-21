package com.ansosoft.item

import org.json4s.{Formats, NoTypeHints}
import org.json4s.ext.EnumSerializer
import pl.newicom.dddd.serialization.{JsonExtraSerHints, JsonSerializationHintsProvider}

/**
  * Created by admin on 10/21/16.
  */
class ItemSerializationHintsProvider extends JsonSerializationHintsProvider {

  val serializers = List(new EnumSerializer(ProductType))

  override def hints(default: Formats) = JsonExtraSerHints(NoTypeHints, serializers)
}
