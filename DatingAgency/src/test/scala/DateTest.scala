
import org.scalatest.funsuite.AnyFunSuite

class DateSuite extends AnyFunSuite:

  test("Oss won't couple with Oss") {
    val oss1 = Oss("John", 10, 1)
    oss1.addHobby("art")
    val oss2 = Oss("Alex", 10, 1)
    oss2.addHobby("art")
    assert(Date(oss1, oss2).wasSuccessful(10) == false)
    assert(Date(oss2, oss1).wasSuccessful(10) == false)
  }

  test("Hun won't couple if no shared hobby") {
    val hun = Hun("John", 10, 1)
    hun.addHobby("art")
    hun.addHobby("sport")
    val oss = Oss("Alex", 10, 1)
    oss.addHobby("music")
    assert(Date(hun, oss).wasSuccessful(10) == false)
    assert(Date(oss, hun).wasSuccessful(10) == false)
  }

  test("Hun couple if one shared hobby") {
    val hun = Hun("John", 5, 5)
    hun.addHobby("art")
    hun.addHobby("sport")
    val oss = Oss("Alex", 5, 6)
    oss.addHobby("art")
    oss.addHobby("music")   
    assert(Date(hun, oss).wasSuccessful(10) == true)
    assert(Date(oss, hun).wasSuccessful(10) == true)
  }

  test("Couple relying on three shared hobbies") {
    val hun = Hun("John", 5, 5)
    hun.addHobby("art")
    hun.addHobby("sport")
    hun.addHobby("music")
    val oss = Oss("Alex", 5, 8)
    oss.addHobby("art")
    oss.addHobby("music")
    oss.addHobby("sport")
    assert(Date(hun, oss).wasSuccessful(10) == true)
    assert(Date(oss, hun).wasSuccessful(10) == true)
  }

  test("One shared hobby too few to couple") {
    val hun = Hun("John", 5, 5)
    hun.addHobby("art")
    hun.addHobby("sport")
    val oss = Oss("Alex", 5, 8)
    oss.addHobby("art")
    oss.addHobby("music")
    oss.addHobby("sport")
    assert(Date(hun, oss).wasSuccessful(10) == false)
    assert(Date(oss, hun).wasSuccessful(10) == false)
  }

  test("Relying on averageRemainingDesirability to couple") {
    val hun = Hun("John", 5, 5)
    hun.addHobby("art")
    hun.addHobby("sport")
    val oss = Oss("Alex", 5, 8)
    oss.addHobby("art")
    oss.addHobby("music")
    oss.addHobby("sport")
    assert(Date(hun, oss).wasSuccessful(6) === true)
    assert(Date(oss, hun).wasSuccessful(6) == true)
  }
