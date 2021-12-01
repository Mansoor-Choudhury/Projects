import org.scalatest.funsuite.AnyFunSuite
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper

class RoomTestSuite extends AnyFunSuite:
  val tolerance = 0.00001

  test("to validate room with no windows throw execption"){
    var flag = 0
    val circle = new Circle(4)
    try
      val singleRoom = new SingleRoom(1, -2, 1, circle)
    catch
      case e: IllegalArgumentException => flag = 1
    assert(flag == 1)
  }

  test("to validate room with no doors throw execption"){
    var flag = 0
    val rectangle = Rectangle(4,6)
    try
      val singleRoom = new SingleRoom(1, 2, -9, rectangle)
    catch
      case e: IllegalArgumentException => flag = 1
    assert(flag == 1)
  }

  test("to check the area of returned room is the area of circle"){
    var singleRoom = SingleRoom(1,1,2,Circle(2.5))
    assert(singleRoom.area === 19.63495 +- tolerance)
  }

  test("to check the area of returned room is the area of rectangle"){
    var singleRoom = SingleRoom(1,1,2,Rectangle(4,8))
    assert(singleRoom.area === 32)
  }