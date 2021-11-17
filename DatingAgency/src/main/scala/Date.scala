
class Date(single1: Single, single2: Single):

  def wasSuccessful(avgDesirability: Double): Boolean =
    single1.agreeTo(single2, avgDesirability) && single2.agreeTo(single1, avgDesirability)
