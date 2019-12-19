package messagePassing

import java.util.UUID

case class Message(id: UUID, value: String)

object Message {
  def fromString(value: String): Message = {
    val id = UUID.randomUUID()
    Message(id, value)
  }
}
