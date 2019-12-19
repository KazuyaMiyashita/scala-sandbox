package messagePassing

class NodeFactory(
    messagePrinter: MessagePrinter
) {

  def fromName(name: String): Node = {
    new Node(name, messagePrinter)
  }

}
