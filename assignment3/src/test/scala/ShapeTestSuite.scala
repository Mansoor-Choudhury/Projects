import org.scalatest.funsuite.AnyFunSuite
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper

class ShapeTestSuite extends AnyFunSuite:

  val tolerance = 0.00001

  test("to check the area of rectangle is valid"){
      val rectangle = Rectangle(5,8)
      assert(rectangle.area === 40)
  }

  test("to check the area of circle is valid"){
    val circle = Circle(5)
    assert(circle.area === 78.53981 +- tolerance)
  }
