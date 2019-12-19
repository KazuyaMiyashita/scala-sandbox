package multipleResources

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalamock.scalatest.MockFactory

import cats.Id
import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class MultipleResourcesSpec extends FlatSpec with Matchers with ScalaFutures with MockFactory {

  "MultipleResourecs[Id]" should "fetch from cache" in {

    val cache: Repository[Id]    = stub[Repository[Id]]
    val database: Repository[Id] = stub[Repository[Id]]

    (cache.fetch _).when().returns(Some(Target("hoge")))

    val multipleResources: MultipleResources[Id] = new MultipleResources[Id](cache, database)

    multipleResources.fetch() shouldEqual Some(Target("hoge"))

  }

  "MultipleResourecs[Id]" should "fetch from database" in {

    val cache: Repository[Id]    = stub[Repository[Id]]
    val database: Repository[Id] = stub[Repository[Id]]

    (cache.fetch _).when().returns(None)
    (database.fetch _).when().returns(Some(Target("hoge")))

    val multipleResources: MultipleResources[Id] = new MultipleResources[Id](cache, database)

    multipleResources.fetch() shouldEqual Some(Target("hoge"))

  }

  "MultipleResourecs[Id]" should "fetch none" in {

    val cache: Repository[Id]    = stub[Repository[Id]]
    val database: Repository[Id] = stub[Repository[Id]]

    (cache.fetch _).when().returns(None)
    (database.fetch _).when().returns(None)

    val multipleResources: MultipleResources[Id] = new MultipleResources[Id](cache, database)

    multipleResources.fetch() shouldEqual None

  }

  "MultipleResourecs[Future]" should "fetch from cache" in {

    val cache: Repository[Future]    = stub[Repository[Future]]
    val database: Repository[Future] = stub[Repository[Future]]

    (cache.fetch _).when().returns(Future.successful(Some(Target("hoge"))))

    val multipleResources: MultipleResources[Future] = new MultipleResources[Future](cache, database)

    multipleResources.fetch().futureValue shouldEqual Some(Target("hoge"))

  }

  "MultipleResourecs[Future]" should "fetch from database" in {

    val cache: Repository[Future]    = stub[Repository[Future]]
    val database: Repository[Future] = stub[Repository[Future]]

    (cache.fetch _).when().returns(Future.successful(None))
    (database.fetch _).when().returns(Future.successful(Some(Target("hoge"))))

    val multipleResources: MultipleResources[Future] = new MultipleResources[Future](cache, database)

    multipleResources.fetch().futureValue shouldEqual Some(Target("hoge"))

  }

  "MultipleResourecs[Future]" should "fetch none" in {

    val cache: Repository[Future]    = stub[Repository[Future]]
    val database: Repository[Future] = stub[Repository[Future]]

    (cache.fetch _).when().returns(Future.successful(None))
    (database.fetch _).when().returns(Future.successful(None))

    val multipleResources: MultipleResources[Future] = new MultipleResources[Future](cache, database)

    multipleResources.fetch().futureValue shouldEqual None

  }

}
