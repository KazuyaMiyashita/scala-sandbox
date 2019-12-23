package range

trait PolyRange[T] {

  def begin: T
  def end: T
  def ordering: Ordering[T]
  final def contains(elem: T): Boolean = ordering.lteq(begin, elem) && ordering.lteq(elem, end)

}

object PolyRange {

  def apply[T](_begin: T, _end: T)(implicit _ordering: Ordering[T]): PolyRange[T] = new PolyRange[T] {
    override val begin    = _begin
    override val end      = _end
    override val ordering = _ordering
  }

}
