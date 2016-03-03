class CutrodRecursive {
   
    def cuttingRod(length:Int, prices: List[Int]): Int =
	{
		if (length < 2)
			return prices(0)
		(1 to length/2).foldLeft(prices(length - 1))((maxPrice, index) =>
                         Math.max(maxPrice, cuttingRod(index, prices) + cuttingRod(length - index, prices)))
	}

}

class CutrodMemoize extends CutrodRecursive {

   def cuttingRodMemoize(length:Int, prices: List[Int], memoizedList:List[Int]): Int = {
		if ( memoizedList(prices.size-1) > 0)
			return memoizedList(prices.size-1)
		val memoize = new CutrodMemoize() {
		override def cuttingRod(length: Int, prices: List[Int]): Int = {
			if (memoizedList(length) > 0)
				return memoizedList(length)
			super.cuttingRod(length, prices)
		}
	}
   cuttingRodMemoize(length-1, prices, memoizedList.updated(length-1, memoize.cuttingRod(length, prices)))
  }
}

def timeCode(functionCall: () => Int): Tuple2[Int,Double] =
{
	val start = System.nanoTime()
	val returnValue = functionCall()
	val end = System.nanoTime()
	new Tuple2[Int,Double](returnValue, ((end - start).toDouble/1000000))
}
		
val prices = List(2, 5, 9, 11, 15, 16, 19, 22)
val a = 0
for( a <- 1 to 5)
{
	println("\nRecursive solution : ")
	val results = timeCode(() => new CutrodRecursive().cuttingRod(prices.size, prices))
	println("Maximum price is : " + results._1)
	println("Total time of execution : " + results._2 + " milliseconds")

	println("\nMemoize solution : ")
	val result = timeCode(() => new CutrodMemoize().cuttingRodMemoize(prices.size, prices,List.fill(prices.size+1)(0)))
	println("Maximum price is : " + result._1)
	println("Total time of execution : " + result._2 + " milliseconds")
		
}

