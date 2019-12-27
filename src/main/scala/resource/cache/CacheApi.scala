package resource.cache

trait CacheApi[K, V] {
  self =>

  def get(key: K): Option[V]
  def mget(keys: Set[K]): Map[K, V]
  def set(key: K, value: V): Unit
  def delete(key: K): Unit

  def keyChain: List[K]

  final def mapKey[KK](kc: KeyConverter[K, KK]): CacheApi[KK, V] = {
    new CacheApi[KK, V] {
      override def get(key: KK): Option[V] = {
        self.get(kc.decoder(key))
      }
      override def mget(keys: Set[KK]): Map[KK, V] = {
        self.mget(keys.map(kc.decoder)).map {
          case (k, v) => (kc.encoder(k), v)
        }
      }
      override def set(key: KK, value: V): Unit = {
        self.set(kc.decoder(key), value)
      }
      override def delete(key: KK): Unit = {
        self.delete(kc.decoder(key))
      }

      override def keyChain: List[KK] = self.keyChain.map(kc.encoder)
    }
  }

}

trait KeyConverter[K, KK] {
  def encoder: K => KK
  def decoder: KK => K
}

class PrimitiveRedis extends CacheApi[String, String] {

  import scala.collection.mutable
  private val map: mutable.Map[String, String] = mutable.HashMap.empty

  override def get(key: String): Option[String] = map.get(key)
  override def mget(keys: Set[String]): Map[String, String] =
    keys.flatMap { key =>
      get(key) match {
        case None        => Nil
        case Some(value) => List((key, value))
      }
    }.toMap
  override def set(key: String, value: String): Unit = map.addOne(key -> value)
  override def delete(key: String): Unit             = map.remove(key)

  override val keyChain: List[String] = Nil

}
