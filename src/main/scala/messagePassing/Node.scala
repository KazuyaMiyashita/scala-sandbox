package messagePassing
import scala.collection.immutable.HashSet
import java.util.UUID

class Node(val name: String) {

  private var knownNodes: Set[Node]           = Set.empty
  private var receivedMessages: List[Message] = Nil
  def getReceivedMessages                     = receivedMessages
  private var handledMessageIdSet: Set[UUID]  = HashSet.empty
  def getHandledMessageIdSet                  = handledMessageIdSet

  def knows(node: Node): Unit = {
    if (node != this) knownNodes += node
  }

  def sendMessage(message: Message): Unit = {
    handledMessageIdSet += message.id
    knownNodes.foreach { n =>
      n.receiveMessage(message)
    }
  }

  def receiveMessage(message: Message): Unit = {
    if (!handledMessageIdSet.contains(message.id)) {
      handledMessageIdSet += message.id
      receivedMessages = message :: receivedMessages
      println(s"""[${this.name}] received message "${message.value}"""")
      sendMessage(message)
    }
  }

  override def hashCode(): Int = name.##
  override def equals(obj: Any): Boolean = obj match {
    case that: Node => this.name == that.name
    case _          => false
  }

}
