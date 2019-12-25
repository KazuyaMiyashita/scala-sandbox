package resource

trait Resource[K, V] {
  self =>

  def get(key: K): Option[V]

  def set(key: K, value: V): Unit

  final def withNext[KK, VV](
      next: Resource[KK, VV]
  )(k2kk: K => KK, kk2k: KK => K, v2vv: V => VV, vv2v: VV => V): Resource[K, V] = {
    new ResourceChain[K, V](this, next.mapKV(kk2k, k2kk, vv2v, v2vv))
  }

  final def mapKV[KK, VV](k2kk: K => KK, kk2k: KK => K, v2vv: V => VV, vv2v: VV => V) = new Resource[KK, VV] {
    override def get(key: KK): Option[VV]      = self.get(kk2k(key)).map(v2vv(_))
    override def set(key: KK, value: VV): Unit = self.set(kk2k(key), vv2v(value))
  }

}

class ResourceChain[K, V](
    first: Resource[K, V],
    second: Resource[K, V]
) extends Resource[K, V] {

  override def get(key: K): Option[V] = {
    first.get(key).orElse {
      second.get(key)
    }
  }

  override def set(key: K, value: V) = {
    first.set(key, value)
    second.set(key, value)
  }

}

class DummyResource[K, V] extends Resource[K, V] {

  import scala.collection.mutable

  private val map: mutable.Map[K, V] = mutable.Map.empty

  override def get(key: K)           = map.get(key)
  override def set(key: K, value: V) = map.addOne(key -> value)

}
