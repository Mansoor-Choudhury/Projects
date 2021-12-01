class Hotel (name: String, rooms: Room*):

   object Hotel {
     def unapply(hotel: Hotel): Seq[Room] = rooms;
   }