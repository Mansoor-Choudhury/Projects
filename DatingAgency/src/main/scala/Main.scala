
import scala.io.Source

@main def hello: Unit =
  val datingAgency = DatingAgency()
  val desirabilityRange = Range(1, 11)
  val hobbies = Array("art", "sport", "music")
  val filename = "singles.txt"

  for (line <- Source.fromFile(filename).getLines)
    val data = line.split("\\W+")
    val singleType = data(0)
    require(singleType == "o" || singleType == "h")
    val name = data(1)
    val desirability = data(2).toInt
    require(desirabilityRange.contains(desirability))
    val soughtDesirability = data(3).toInt
    require(desirabilityRange.contains(soughtDesirability))
    val numberOfHobbies = data.length - 4
    require (numberOfHobbies <= 3)
    val single:Single = singleType match
      case "o" => Oss(name, desirability, soughtDesirability)
      case "h" => Hun(name, desirability, soughtDesirability)
    for i <- 4 to data.length-1 do
      require(hobbies.contains(data(i)))
      single.addHobby(data(i))
    datingAgency.addSingle(single)

  datingAgency.performDating
  println(datingAgency.toString)
  