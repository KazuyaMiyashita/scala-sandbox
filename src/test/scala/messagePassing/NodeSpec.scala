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

    n1.sendMessage("hello")

    n2.getReceivedMessages shouldEqual List("hello")
    n3.getReceivedMessages shouldEqual List("hello")
    n4.getReceivedMessages shouldEqual List("hello")
    n5.getReceivedMessages shouldEqual List("hello")

  }

}
