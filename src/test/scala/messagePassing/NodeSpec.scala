package messagePassing

import org.scalatest._
import org.scalamock.scalatest.MockFactory

class NodeSpec extends FunSuite with Matchers with MockFactory {

  test("Node.sendMessage") {

    val messagePrinter: MessagePrinter = mock[MessagePrinter]
    val nodeFactory                    = new NodeFactory(messagePrinter)

    val n1 = nodeFactory.fromName("n1")
    val n2 = nodeFactory.fromName("n2")
    val n3 = nodeFactory.fromName("n3")
    val n4 = nodeFactory.fromName("n4")
    val n5 = nodeFactory.fromName("n5")

    n1 knows n2
    n1 knows n3
    n1 knows n4
    n1 knows n5

    val message = Message.fromString("hello")

    (messagePrinter.print _).expects(n2, message)
    (messagePrinter.print _).expects(n3, message)
    (messagePrinter.print _).expects(n4, message)
    (messagePrinter.print _).expects(n5, message)

    n1.sendMessage(message)

  }

  test("Node.sendMessage 2") {

    val messagePrinter: MessagePrinter = mock[MessagePrinter]
    val nodeFactory                    = new NodeFactory(messagePrinter)

    val n1 = nodeFactory.fromName("n1")
    val n2 = nodeFactory.fromName("n2")
    val n3 = nodeFactory.fromName("n3")
    val n4 = nodeFactory.fromName("n4")
    val n5 = nodeFactory.fromName("n5")

    n1 knows n2
    n2 knows n3
    n3 knows n4
    n4 knows n5

    val message = Message.fromString("hello2")

    (messagePrinter.print _).expects(n2, message)
    (messagePrinter.print _).expects(n3, message)
    (messagePrinter.print _).expects(n4, message)
    (messagePrinter.print _).expects(n5, message)

    n1.sendMessage(message)

  }

  test("Node.sendMessage 3") {

    val messagePrinter: MessagePrinter = mock[MessagePrinter]
    val nodeFactory                    = new NodeFactory(messagePrinter)

    val n1 = nodeFactory.fromName("n1")
    val n2 = nodeFactory.fromName("n2")
    val n3 = nodeFactory.fromName("n3")

    n1 knows n2
    n2 knows n3
    n3 knows n1

    val message = Message.fromString("hello3")

    (messagePrinter.print _).expects(n2, message)
    (messagePrinter.print _).expects(n3, message)

    n1.sendMessage(message)

  }

  test("Node.sendMessage 4") {

    val messagePrinter: MessagePrinter = mock[MessagePrinter]
    val nodeFactory                    = new NodeFactory(messagePrinter)

    val n1 = nodeFactory.fromName("n1")
    val n2 = nodeFactory.fromName("n2")
    val n3 = nodeFactory.fromName("n3")
    val n4 = nodeFactory.fromName("n4")

    n1 knows n2
    n2 knows n3
    n2 knows n4

    val message = Message.fromString("hello4")

    (messagePrinter.print _).expects(n2, message)
    (messagePrinter.print _).expects(n3, message)
    (messagePrinter.print _).expects(n4, message)

    n1.sendMessage(message)

  }

}
