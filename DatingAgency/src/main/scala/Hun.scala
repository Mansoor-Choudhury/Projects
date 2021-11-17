
class Hun (name: String, desirability: Int, soughtDesirability: Int)
  extends Single(name, desirability, soughtDesirability):

  override def agreeTo(other: Single, avgDesirability: Double): Boolean =
    super.agreeTo(other, avgDesirability) && numberSharedHobbies(other) > 0