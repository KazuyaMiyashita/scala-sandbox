package range

import org.scalatest._

class PolyRangeSpec extends FunSuite with Matchers {

  test("Instant") {

    import java.time.Instant

    val begin                     = Instant.parse("2007-12-03T10:15:30.00Z")
    val end                       = Instant.parse("2008-01-03T10:15:30.00Z")
    val range: PolyRange[Instant] = PolyRange(begin, end)

    range.contains(Instant.parse("2000-12-03T10:15:30.00Z")) shouldEqual false
    range.contains(Instant.parse("2007-12-03T10:15:30.00Z")) shouldEqual true
    range.contains(Instant.parse("2008-01-01T00:00:00.00Z")) shouldEqual true
    range.contains(Instant.parse("2008-01-03T10:15:30.00Z")) shouldEqual true
    range.contains(Instant.parse("2020-01-01T00:00:00.00Z")) shouldEqual false

  }

}
