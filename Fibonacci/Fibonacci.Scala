//Program to generate fibonacci series

object Fibonacci {
  def immutable(position:Int): List[Int] =
  {
    lazy val fibTemp: Stream[Int] = 1 #:: fibTemp.scanLeft(1)(_ + _).take(position-1)
    fibTemp.toList
  }

  def mutable(position:Int, fibMutable:List[Int]): List[Int] =
  {
    var fibTemp = fibMutable
    var size = 0
    for( size <- 0 to position-1)
    {
      if(size <= 1)
        fibTemp ++= List(1)
      else fibTemp ++= List(fibTemp(size-1) + fibTemp(size-2))
    }
    return fibTemp
  }

  def recursive(position:Int, fibRecMutable:List[Int]): List[Int] =
  {
    var fibTemp= fibRecMutable
    val size:Int = fibTemp.size
    if(size < position)
    {
      if(size <= 1)
        fibTemp ++= List(1)
      else fibTemp ++= List(fibTemp(size-1) + fibTemp(size-2))
      fibTemp = recursive(position , fibTemp)
    }
    fibTemp
  }

  def main(args: Array[String])
  {
    var fibTemp = List[Int]()
    val position:Int = 7
    var value=0
    if(position > 0)
    {
      println("Fibonacci Recursive ")
      fibTemp = recursive(position , fibTemp)
      for(value <- fibTemp)
        println(value)
      println();
      fibTemp=fibTemp.drop(position)
      println("Fibonacci Mutable")
      fibTemp = mutable(position, fibTemp)
      for(value <- fibTemp)
        println(value)
      println();
      fibTemp = fibTemp.drop(position)
      fibTemp = immutable(position)
      println("Fibonacci Immutable")
      for (value <- fibTemp)
        println(value)
    }
    else
      println("Oops! Try again with a valid position");
  }
}
