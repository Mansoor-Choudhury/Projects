
import scala.collection.mutable.ListBuffer

trait Single(val name: String, val desirability: Int, soughtDesirability: Int):
  val hobbies = ListBuffer[String]()
  Single.incrementNumSingles()
  val insertionOrder = Single.numberOfSingles

  def addHobby(hobby: String) =
    hobbies += hobby
  
  def agreeTo(other: Single, avgDesirability: Double): Boolean =
    perceivedOtherDesirability(other) >= soughtDesirability
      || avgDesirability < perceivedOtherDesirability(other)

  def numberSharedHobbies(other: Single): Int =
    var count = 0
    for (hobby1 <- hobbies; hobby2 <- other.hobbies) do
      if hobby1 == hobby2 then count += 1
    count

  def compatibleWithOss = true

  def additionalDesirability(other:Single) = 0

  def perceivedOtherDesirability(other: Single) =
    other.desirability + additionalDesirability(other)

  def fussiness = soughtDesirability - desirability

  override def toString = // testing only
    val singleType = this.getClass.getName
    s"$insertionOrder $name, $singleType, $desirability, $soughtDesirability, ${hobbies.mkString(", ")}"

object Single:
  private var numberOfSingles = 0
  private def incrementNumSingles() = numberOfSingles += 1
  