import java.util
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object HotelServices:
   def totalArea(hotel: Hotel): Double =
     def areaTl(room: Room): Double =
       room match
         case s: SingleRoom => s.area
         case Suite(number,kitchen,rooms @_*) => rooms.map(areaTl(_)).sum
     hotel.Hotel.unapply(hotel).map(areaTl(_)).sum

   def noOfDoors(hotel: Hotel): Int =
     def doorsCal(room: Room): Int =
       room match
         case SingleRoom(number, noOfWindows, noOfDoors, shape)=> noOfDoors
         case Suite(number,kitchen,rooms @_*) => rooms.map(doorsCal(_)).sum
     hotel.Hotel.unapply(hotel).map(doorsCal(_)).sum

   def noOfRoomsWithOneWindowAndDoor(hotel: Hotel): Int =
     def oneDoorWindow(room: Room): Int =
       room match
         case SingleRoom(number, noOfWindows, noOfDoors, shape) => if(noOfDoors == 1 && noOfWindows == 1) then 1 else 0
         case Suite(number,kitchen,rooms @_*) => rooms.map(oneDoorWindow(_)).sum
     hotel.Hotel.unapply(hotel).map(oneDoorWindow(_)).sum

   def noOfLargeCircularRooms(hotel: Hotel, minRadius: Double): Int =
     def circularRooms(room: Room): Int =
       room match
         case SingleRoom(number, noOfWindows, noOfDoors, shape) => if(shape.isInstanceOf[Circle] && shape.asInstanceOf[Circle].radius > minRadius) then 1 else 0
         case Suite(number,kitchen,rooms @_*) => rooms.map(circularRooms(_)).sum
     hotel.Hotel.unapply(hotel).map(circularRooms(_)).sum

   def noOfKitchenettes(hotel: Hotel): Int =
     def kitchenette(room: Room): Int =
       room match
         case Suite(number,kitchen,rooms @_*) => if (kitchen == None) then rooms.map(r => kitchenette(r)).sum else 1 + rooms.map(r => kitchenette(r)).sum
         case _=> 0
     hotel.Hotel.unapply(hotel).map(r => kitchenette(r)).sum

   def numbersOfSuitesWithRectangularRooms(hotel: Hotel): List[Int] =
     var list = List[Int]()
     var ar = new ArrayBuffer[Int]()
     def recengularRooms(room: Room): Int =
       room match
         case Suite(number,kitchen,rooms @_*) => rooms.map(r => if(recengularRooms(r) == 1 && !ar.contains(number)) then ar += number else 0).size
         case SingleRoom(number, noOfWindows, noOfDoors, shape) => if(shape.isInstanceOf[Rectangle]) then 1 else 0
     hotel.Hotel.unapply(hotel).map(r => recengularRooms(r))
     list.appendedAll(ar)