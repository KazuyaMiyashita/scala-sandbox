package resource

trait Converter[A, B] {
  def encode(a: A): B
  def decode(b: B): A
}

trait Contain[T] {
  self =>
  def get: Option[T]
  final def map[U](f: T => U): Contain[U] = new Contain[U] {
    override def get = self.get.map(f)
  }
}

trait Chain[T] extends Contain[T] {
  type U
  def converter: Converter[T, U]
  def parent: Contain[U]
  final def getRecursively: Option[T] = {
    get.orElse {
      parent.get.map(converter.decode)
    }
  }
}

object a {

  val chain = new Chain[String] {
    type U = String
    override val converter = new Converter[String, String] {
      override def encode(a: String) = a
      override def decode(b: String) = b
    }
    override val get = Some("hoge")
    def parent = new Contain[String] {
      def get = Some("fuga")
    }
  }

  case class UserId(value: String)
  case class User(userId: UserId, name: String, age: Int)

  val userId                       = UserId("42")
  val userIdStr                    = userId.value
  val userIdByteArray: Array[Byte] = userIdStr.getBytes("UTF-8")

  val keyStr       = "user/" + userIdStr
  val keyByteArray = keyStr.getBytes("UTF-8")

}
