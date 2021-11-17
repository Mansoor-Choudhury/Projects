
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.tags.HtmlUnitBrowser

class DatingAgencySuite extends AnyFunSuite:

  test("AvgRemainingDesirability: simple case") {
    val datingAgency = DatingAgency()
    val single1:Single  = Hun("John", 7, 0)
    val single2:Single  = Oss("Alex", 8, 0)
    datingAgency.addSingle(single1)
    datingAgency.addSingle(single2)
    assert(7.5 === datingAgency.averageSinglesDesirability())
  }

  test("AvgRemainingDesirability: length 2") {
    val datingAgency = DatingAgency()
    val ignoreSingle1 = Oss("john", 10, 10)
    val ignoreSingle2 = Oss("mike", 10, 10)
    datingAgency.addSingle(ignoreSingle1)
    datingAgency.addSingle(ignoreSingle2)
    val avg_desirability = datingAgency.averageSinglesDesirability(Vector(0, 1))
    assert(0.0 === avg_desirability)
  }

  test("AvgRemainingDesirability: length 4") {
    val datingAgency = DatingAgency()
    val single1 = Oss("john", 7, 10)
    val single2 = Hun("mike", 3, 10)
    val ignoreSingle1 = Oss("john", 10, 10)
    val ignoreSingle2 = Hun("mike", 10, 10)
    datingAgency.addSingle(single1)
    datingAgency.addSingle(single2)
    datingAgency.addSingle(ignoreSingle1)
    datingAgency.addSingle(ignoreSingle2)
    val avg_desirability : Double = datingAgency.averageSinglesDesirability(Vector(2, 3))
    assert(5.0 === avg_desirability)
  }

  test("AvgRemainingDesirability: spurious ignore single") {
    val datingAgency = DatingAgency()
    val single1 = Hun("john", 7, 10)
    val single2 = Hun("mike", 3, 10)
    val ignoreSingle1 = Oss("john", 10, 10)
    val ignoreSingle2 = Oss("mike", 10, 10) // not added to list
    datingAgency.addSingle(single1)
    datingAgency.addSingle(single2)
    val avg_desirability : Double = datingAgency.averageSinglesDesirability(Vector(2, 3))
    assert(5.0 == avg_desirability)
  }

  test("test sort on fussiness") {
    val datingAgency = DatingAgency()
    val single1 = Hun("aaaa", 1, 10)
    val single2 = Hun("bbbb", 3, 8)
    val single3 = Oss("cccc", 4, 6)
    val single4 = Oss("dddd", 5, 5)
    datingAgency.addSingle(single1)
    datingAgency.addSingle(single2)
    datingAgency.addSingle(single3)
    datingAgency.addSingle(single4)
    datingAgency.sortSinglesByFussiness
    val expected = s"Couples:\nSingles:\ndddd\ncccc\nbbbb\naaaa\n"
    assert(expected == datingAgency.toString)
  }

  test("test sort on fussiness/alphabetical") {
    val datingAgency = DatingAgency()
    val single1 = Hun("dddd", 5, 5)
    val single2 = Hun("cccc", 5, 5)
    val single3 = Oss("bbbb", 5, 5)
    val single4 = Oss("aaaa", 5, 5)
    datingAgency.addSingle(single1)
    datingAgency.addSingle(single2)
    datingAgency.addSingle(single3)
    datingAgency.addSingle(single4)
    datingAgency.sortSinglesByFussiness
    val expected = s"Couples:\nSingles:\naaaa\nbbbb\ncccc\ndddd\n"
    assert(expected == datingAgency.toString)
  }

  test("test sort on insertion order") {
    val datingAgency = DatingAgency()
    val single1 = Hun("dddd", 5, 5)
    val single2 = Hun("cccc", 5, 5)
    val single3 = Oss("bbbb", 5, 5)
    val single4 = Oss("aaaa", 5, 5)
    datingAgency.addSingle(single1)
    datingAgency.addSingle(single2)
    datingAgency.addSingle(single3)
    datingAgency.addSingle(single4)
    datingAgency.sortSinglesByFussiness
    datingAgency.sortSinglesByInsertionOrder
    val expected = s"Couples:\nSingles:\ndddd\ncccc\nbbbb\naaaa\n"
    assert(expected == datingAgency.toString)
  }

  test("test dating on empty agency") {
    val datingAgency = DatingAgency()
    datingAgency.performDating
    val expected = s"Couples:\nSingles:\n"
    assert(expected == datingAgency.toString)
  }

  test("test dating with one single") {
    val datingAgency = DatingAgency()
    val single1 = Hun("Alex", 5, 5)
    datingAgency.addSingle(single1)
    datingAgency.performDating
    val expected = s"Couples:\nSingles:\nAlex\n"
    assert(expected == datingAgency.toString)
  }

  test("test dating with two compatible singles") {
    val datingAgency = DatingAgency()
    val single1 = Hun("Bob", 5, 5)
    single1.addHobby("art")
    val single2 = Oss("Alex", 5, 5)
    single2.addHobby("art")
    datingAgency.addSingle(single1)
    datingAgency.addSingle(single2)
    datingAgency.performDating
    val expected = s"Couples:\n(Alex, Bob)\nSingles:\n"
    assert(expected == datingAgency.toString)
  }

  test("general test dataing algorithm") {
    val datingAgency = DatingAgency()
    val single0 = Hun("eeee", 1, 8)
    val single1 = Hun("dddd", 3, 6)
    val single2 = Oss("cccc", 4, 6)
    val single3 = Hun("bbbb", 4, 5)
    val single4 = Oss("aaaa", 5, 5)
    val single5 = Hun("ffff", 8, 3)

    single0.addHobby("art")
    datingAgency.addSingle(single0)

    single1.addHobby("art")
    single1.addHobby("sport")
    datingAgency.addSingle(single1)

    single2.addHobby("art")
    single2.addHobby("sport")
    datingAgency.addSingle(single2)

    single3.addHobby("art")
    single3.addHobby("sport")
    datingAgency.addSingle(single3)

    single4.addHobby("art")
    single4.addHobby("sport")
    datingAgency.addSingle(single4)

    single5.addHobby("art")
    datingAgency.addSingle(single5)

    datingAgency.performDating
    val expected = s"Couples:\n(aaaa, dddd)\nSingles:\neeee\ncccc\nbbbb\nffff\n"
    assert(expected == datingAgency.toString)
  }
