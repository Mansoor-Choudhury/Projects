
class Couple(single1: Single, single2: Single):

  override def toString: String =
    s"(${single1.name}, ${single2.name})"