case class Circle (radius: Double) extends Shape:
  override def area: Double =
    3.141592653 * radius * radius