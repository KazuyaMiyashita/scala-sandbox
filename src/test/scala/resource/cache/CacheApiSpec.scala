// package resource.cache

// import org.scalatest._

// class CacheApiSpec extends FlatSpec with Matchers {

//   "CacheApi" should "mapKey" in {

//     val primitiveRedis: CacheApi[String, String] = new PrimitiveRedis
//     val keyIntRedis: CacheApi[Int, String] = primitiveRedis.mapKey(
//       new KeyConverter[String, Int] {
//         val encoder: String => Int = _.toInt
//         val decoder: Int => String = _.toString
//       }
//     )

//   }

// }
