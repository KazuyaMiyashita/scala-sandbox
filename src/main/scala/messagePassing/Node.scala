package messagePassing

class Node(val name: String) {

  private var knownNodes: Set[Node]          = Set.empty
  private var receivedMessages: List[String] = Nil
  def getReceivedMessages                    = receivedMessages

  def knows(node: Node): Unit = {
    if (node != this) knownNodes += node
  }

  def sendMessage(message: String): Unit = {
    knownNodes.foreach { n =>
      n.receiveMessage(message)
    }
  }

  def receiveMessage(message: String): Unit = {
    receivedMessages = message :: receivedMessages
    println(s"""[${this.name}] received message "${message}"""")
  }

  override def hashCode(): Int = name.##
  override def equals(obj: Any): Boolean = obj match {
    case that: Node => this.name == that.name
    case _          => false
  }

}
