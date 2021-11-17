
class Oss(name: String, desirability: Int, soughtDesirability: Int)
  extends Single(name, desirability, soughtDesirability):

  override def compatibleWithOss = false

  override def additionalDesirability(other:Single) =
    numberSharedHobbies(other)

  override def agreeTo(other: Single, avgDesirability: Double): Boolean =
    super.agreeTo(other, avgDesirability) && other.compatibleWithOss