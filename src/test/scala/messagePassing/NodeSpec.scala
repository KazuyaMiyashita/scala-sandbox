package messagePassing

import org.scalatest._

class NodeSpec extends FunSuite with Matchers {

  test("Node.sendMessage") {

    val n1 = new Node("n1")
    val n2 = new Node("n2")
    val n3 = new Node("n3")
    val n4 = new Node("n4")
    val n5 = new Node("n5")

    n1 knows n2
    n1 knows n3
    n1 knows n4
    n1 knows n5

    val message = Message.fromString("hello")
    n1.sendMessage(message)

    n2.getReceivedMessages shouldEqual List(message)
    n3.getReceivedMessages shouldEqual List(message)
    n4.getReceivedMessages shouldEqual List(message)
    n5.getReceivedMessages shouldEqual List(message)

  }

  test("Node.sendMessage 2") {

    val n1 = new Node("n1")
    val n2 = new Node("n2")
    val n3 = new Node("n3")
    val n4 = new Node("n4")
    val n5 = new Node("n5")

    n1 knows n2
    n2 knows n3
    n3 knows n4
    n4 knows n5

    val message = Message.fromString("hello2")
    n1.sendMessage(message)

    n2.getReceivedMessages shouldEqual List(message)
    n3.getReceivedMessages shouldEqual List(message)
    n4.getReceivedMessages shouldEqual List(message)
    n5.getReceivedMessages shouldEqual List(message)

  }

  test("Node.sendMessage 3") {

    val n1 = new Node("n1")
    val n2 = new Node("n2")
    val n3 = new Node("n3")

    n1 knows n2
    n2 knows n3
    n3 knows n1

    val message = Message.fromString("hello3")
    n1.sendMessage(message)

    n1.getReceivedMessages shouldEqual Nil
    n2.getReceivedMessages shouldEqual List(message)
    n3.getReceivedMessages shouldEqual List(message)

  }

  test("Node.sendMessage 4") {

    val n1 = new Node("n1")
    val n2 = new Node("n2")
    val n3 = new Node("n3")
    val n4 = new Node("n4")

    n1 knows n2
    n2 knows n3
    n2 knows n4

    val message = Message.fromString("hello4")
    n1.sendMessage(message)

    n1.getReceivedMessages shouldEqual Nil
    n2.getReceivedMessages shouldEqual List(message)
    n3.getReceivedMessages shouldEqual List(message)
    n4.getReceivedMessages shouldEqual List(message)

  }

}
