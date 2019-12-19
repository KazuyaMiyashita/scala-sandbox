package traits

import java.util.UUID

class Obj extends Id

trait Id {
  val id = UUID.randomUUID()
}
