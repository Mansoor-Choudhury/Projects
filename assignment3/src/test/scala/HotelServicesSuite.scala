import org.scalatest.funsuite.AnyFunSuite
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper

class HotelServicesSuite extends AnyFunSuite:

  val tolerance = 0.00001

  val testHotel1 = Hotel("",
    SingleRoom(101, 2, 3, Rectangle(6, 3)),
    Suite(1, None,
      SingleRoom(102, 2, 3, Rectangle(4, 7)),
      SingleRoom(103, 2, 2, Circle(3.5)),
      Suite(2, Some(Kitchenette()),
        SingleRoom(105, 2, 3, Rectangle(4, 7)),
        SingleRoom(106, 2, 2, Rectangle(4, 7))),
      SingleRoom(107, 1, 1, Circle(8.9)),
    ),
  )

  val testHotel2 = Hotel("JW Marriot",
    Suite(1, Some(Kitchenette()),
      SingleRoom(102, 2, 3, Rectangle(4, 7)),
      SingleRoom(103, 2, 2, Circle(3.5)),
      Suite(2, None,
        SingleRoom(105, 2, 3, Circle(4.7)),
        SingleRoom(106, 2, 2, Rectangle(4, 7)),
        Suite(3, None,
          SingleRoom(107, 2, 3, Rectangle(4, 7)),
          Suite(4, Some(Kitchenette()),
            SingleRoom(108, 2, 3, Rectangle(4, 7))))),
      SingleRoom(109, 1, 1, Circle(5.4)),
    ),SingleRoom(201, 1, 1, Circle(7.9)),
  )

  test("number of doors correct") {
    assert(HotelServices.noOfDoors(testHotel1) === 14)
  }

  test("hotel area correct") {
    assert(HotelServices.totalArea(testHotel1) === 389.330064 +- tolerance)
  }

  test("noOfRoomsWithOneWindowAndDoor correct") {
    assert(HotelServices.noOfRoomsWithOneWindowAndDoor(testHotel1) === 1)
  }

  test("noOfLargeCircularRooms correct") {
    assert(HotelServices.noOfLargeCircularRooms(testHotel1, 8.0) === 1)
  }

  test("noOfKitchenettes correct") {
    assert(HotelServices.noOfKitchenettes(testHotel1) === 1)
  }

  test("numbersOfSuitesWithRectangularRooms correct") {
    val roomList = HotelServices.numbersOfSuitesWithRectangularRooms(testHotel1)
    var roomSet = Set.empty[Int]
    roomList.foreach(num => roomSet += num)
    assert(roomList.length === 2 && roomSet === Set(2, 1))
  }

  test("to check the total number of doors are correct"){
    assert(HotelServices.noOfDoors(testHotel2) === 18)
  }

  test("to check the total area of hotel is correct"){
    assert(HotelServices.totalArea(testHotel2) === 507.55793 +- tolerance)
  }

  test("to check the total number of hotal rooms having one door and window"){
    assert(HotelServices.noOfRoomsWithOneWindowAndDoor(testHotel2) == 2)
  }

  test("to check the total number of circular rooms are correct"){
    assert(HotelServices.noOfLargeCircularRooms(testHotel2,4.0) == 3)
  }

  test("to check the total number of kitchenette in suite are valid"){
    assert(HotelServices.noOfKitchenettes(testHotel2) == 2)
  }

  test("to check the numbersOfSuitesWithRectangularRooms method is workimg correctly"){
    assert(HotelServices.numbersOfSuitesWithRectangularRooms(testHotel2).size == 4)
  }