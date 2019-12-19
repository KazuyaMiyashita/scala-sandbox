package algo

import org.scalatest._

class ABC147_ASpec extends FlatSpec with Matchers {

  import ABC147_A._

  "input 5 7 9" should "output win" in {

    solve(5, 7, 9) shouldEqual "win"

  }

  "input 13 7 2" should "output bust" in {

    solve(13, 7, 2) shouldEqual "bust"

  }

}
