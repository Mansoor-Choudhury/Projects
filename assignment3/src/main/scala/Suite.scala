case class Suite (number: Int, kitchen: Option[Kitchenette],
              rooms: Room*) extends Room{
}