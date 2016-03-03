import scala.math.pow

object Hanoi {

  def printStands(lists: List[List[List[Int]]]): Unit =
  {
    for(value <- lists) {
      for (index <- value) {
        if (index.isEmpty) print(".") else index.foreach(print); print(" ")
      }
      println
    }
  }

  def moveDisk(list:List[List[List[Int]]], from:Int, to:Int): List[List[List[Int]]] = {
    val ln = list.length - 1;
    list ::: List(list(ln).updated(to, list(ln)(to) :+ (list(ln)(from).last)).updated(from, list(ln)(from).dropRight(1)))
  }

  def iterative(nDisks:Int, list:List[List[List[Int]]]): List[List[List[Int]]]  = {
    (1 to (pow(2, nDisks) - 1).toInt).foldLeft(list)((nList, I) =>moveDisk(nList, (I & I - 1) % 3, ((I | (I - 1)) + 1) % 3) )
  }

  def tail_recursion(nDisks:Int, list:List[List[List[Int]]], init:Int): List[List[List[Int]]]  = {
    val lists:List[List[List[Int]]] = list
    val x = init + 1 //# assume the next step
    val from = (x & x-1)% 3
    val to = ((x|(x-1))+1)%3
    val ln = lists.length - 1
    val nList = lists ::: List(lists(ln).updated(to, lists(ln)(to) :+ (lists(ln)(from).last)).updated(from, lists(ln)(from).dropRight(1)))
    if(nList(nList.length - 1).last.length >= nDisks)
      return nList
    tail_recursion(nDisks, nList, x)
  }

  def towerOfHanoiRecursive(nDisk:Int, bar1:Int, bar2:Int, bar3:Int, list:List[List[List[Int]]]):List[List[List[Int]]] =
  {
    val lists:List[List[List[Int]]] = list
    if(nDisk == 1) {
      val len = lists.length - 1
      lists ::: List(lists(len).updated(bar3, lists(len)(bar3) :+ nDisk).updated(bar1, lists(len)(bar1).dropRight(1)))
    }else {
      val nList = towerOfHanoiRecursive(nDisk - 1, bar1, bar3, bar2, lists)
      val nLen = nList.length - 1
      val sList = nList(nLen).updated(bar3, nList(nLen)(bar3) :+ nDisk).updated(bar1, nList(nLen)(bar1).dropRight(1))
      towerOfHanoiRecursive(nDisk - 1, bar2, bar1, bar3, nList ::: List(sList))
    }
  }

  def main(args:Array[String]): Unit =
  {
    val nDisks:Int = 5
    val lists = List(List((1 to nDisks).toList.reverse, List(), List()))
    println("Towers of Hanoi Recursive")
    printStands(towerOfHanoiRecursive(nDisks, 0, 1, 2, lists))
    println("Towers of Hanoi Iterative")
    printStands(iterative(nDisks, lists))
    println("Towers of Hanoi Tail Recursion")
    printStands(tail_recursion(nDisks, lists, 0))
  }
}