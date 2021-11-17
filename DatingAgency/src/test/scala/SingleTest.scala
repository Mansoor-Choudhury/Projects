
import org.scalatest.funsuite.AnyFunSuite

class SingleSuite extends AnyFunSuite:

  test("numberSharedHobbies with zero hobbies each") {
    val single1 = Hun("John", 5, 10)
    val single2 = Hun("Alex", 5, 10)
    assert(single1.numberSharedHobbies(single2) == 0)
  }

  test("numberSharedHobbies with two hobbies each, one in common") {
    val single1 = Hun("John", 5, 10)
    val single2 = Hun("Alex", 5, 10)
    single1.addHobby("music")
    single1.addHobby("art")
    single2.addHobby("music")
    single2.addHobby("sport")
    assert(single1.numberSharedHobbies(single2) == 1)
  }

  test("numberSharedHobbies with three hobbies each, all in common") {
    val single1 = Hun("John", 5, 10)
    val single2 = Hun("Alex", 5, 10)
    single1.addHobby("music")
    single1.addHobby("art")
    single1.addHobby("sport")
    single2.addHobby("music")
    single2.addHobby("sport")
    single2.addHobby("art")
    assert(single1.numberSharedHobbies(single2) == 3)
  }

  test("willCoupleWithOss works correctly") {
    val single1 = Hun("John", 5, 10)
    val single2 = Oss("Alex", 5, 10)
    assert(single1.compatibleWithOss == true)
    assert(single2.compatibleWithOss == false)
  }

  test("perceivedOtherDesirability, no hobbies in common") {
    val single1 = Hun("John", 5, 10)
    val single2 = Oss("Alex", 6, 10)
    assert(single1.perceivedOtherDesirability(single2) == 6)
    assert(single2.perceivedOtherDesirability(single1) == 5)
  }

  test("perceivedOtherDesirability, one hobby in common") {
    val single1 = Hun("John", 5, 10)
    val single2 = Oss("Alex", 6, 10)
    single1.addHobby("music")
    single1.addHobby("art")
    single2.addHobby("music")
    single2.addHobby("sport")
    assert(single1.perceivedOtherDesirability(single2) === 6)
    assert(single2.perceivedOtherDesirability(single1) == 6)
  }

  test("perceivedOtherDesirability, three hobbies in common") {
    val single1 = Hun("John", 5, 10)
    val single2 = Oss("Alex", 6, 10)
    single1.addHobby("music")
    single1.addHobby("art")
    single1.addHobby("sport")
    single2.addHobby("music")
    single2.addHobby("art")
    single2.addHobby("sport")
    assert(single1.perceivedOtherDesirability(single2) === 6)
    assert(single2.perceivedOtherDesirability(single1) == 8)
  }

  test("basic fussiness") {
    val single = Hun("John", 5, 10)
    assert(single.fussiness == 5)
  }

  test("minimum fussiness") {
    val single = Hun("John", 10, 1)
    assert(single.fussiness == -9)
  }

  test("maximum fussiness") {
    val single = Hun("John", 1, 10)
    assert(single.fussiness == 9)
  }