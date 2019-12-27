package resource

import org.scalatest._

class ResourceSpec extends FlatSpec with Matchers {

  "Resource" should "hoge" in {

    val parent: Resource[String, String] = new DummyResource
    val child: Resource[String, String] =
      (new DummyResource).withNext(parent)(identity, identity, identity, identity)

    parent.set("hoge", "HOGE")
    child.get("hoge") shouldEqual Some("HOGE")

  }

  "Resource" should "hoge2" in {

    val parent: Resource[List[Byte], List[Byte]] = new DummyResource
    val child: Resource[String, String] =
      (new DummyResource).withNext(parent)(
        _.getBytes("UTF-8").toList,
        (a: List[Byte]) => { new String(a.toArray, "UTF-8") },
        _.getBytes("UTF-8").toList,
        (a: List[Byte]) => { new String(a.toArray, "UTF-8") }
      )

    parent.set("hoge".getBytes("UTF-8").toList, "HOGE".getBytes("UTF-8").toList)
    child.get("hoge") shouldEqual Some("HOGE")

  }

  "Resource" should "hoge3" in {

    val parent: Resource[String, String] = new DummyResource
    val child: Resource[String, String] =
      (new DummyResource).withNext(parent)(identity, identity, identity, identity)

    parent.set("hoge", "HOGE")
    child.get("hoge") shouldEqual Some("HOGE")

  }

  "Resource" should "hoge4" in {

    case class UserId(value: Int)
    case class User(name: String)
    case class ImageId(value: Int)
    case class Image(url: String)

    val rdb: Resource[UserId, User]     = new DummyResource
    val aws: Resource[ImageId, Image]   = new DummyResource
    val redis: Resource[String, String] = new DummyResource
    val userResource: Resource[UserId, User] = redis
      .mapKV[UserId, User](
        address => { UserId(address.drop(5).toInt) },
        userId => { "user/" + userId.value.toString },
        name => { User(name) },
        user => { user.name }
      )
      .withNext(rdb)(
        identity,
        identity,
        identity,
        identity
      )
    val imageResource: Resource[ImageId, Image] = redis
      .mapKV[ImageId, Image](
        address => { ImageId(address.drop(6).toInt) },
        imageId => { "image/" + imageId.value.toString },
        url => { Image(url) },
        image => { image.url }
      )
      .withNext(aws)(
        identity,
        identity,
        identity,
        identity
      )

    rdb.set(UserId(42), User("Bob"))
    aws.set(ImageId(1), Image("http://www.example.com/1.jpg"))

    userResource.get(UserId(42)) shouldEqual Some(User("Bob"))
    userResource.get(UserId(43)) shouldEqual None
    imageResource.get(ImageId(1)) shouldEqual Some(Image("http://www.example.com/1.jpg"))
    imageResource.get(ImageId(2)) shouldEqual None

  }

}
