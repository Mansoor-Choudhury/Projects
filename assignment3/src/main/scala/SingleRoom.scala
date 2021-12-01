case class SingleRoom (number: Int, noOfWindows: Int,
                   noOfDoors: Int, shape: Shape) extends Room:
  if noOfWindows < 0 || noOfDoors < 0 then
    throw new IllegalArgumentException("Illegal hotel: Doors shoule be >= 1 and Windows >=1")

  def area: Double =
    shape match
      case rectangle: Rectangle => rectangle.area
      case circle: Circle => circle.area