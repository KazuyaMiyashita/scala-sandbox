package messagePassing

trait MessagePrinter {
  def print(node: Node, message: Message): Unit
}

object ConsoleMessagePrinter extends MessagePrinter {
  override def print(node: Node, message: Message): Unit = {
    println(s"""[${node.name}] received message "${message.value}"""")
  }
}
