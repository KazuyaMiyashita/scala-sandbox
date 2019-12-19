package traits

import org.scalatest._

class ObjSpec extends FunSuite with Matchers {

  test("a") {

    val obj1 = new Obj
    val obj2 = new Obj

    obj1.id shouldNot equal(obj2.id)

  }

}
