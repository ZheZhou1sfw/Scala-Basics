package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,4,2)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // get index 2 (0-indexed)
  println(aSequence ++ Seq(7, 5, 6))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  val anotherRange: Seq[Int] = 1 until 10

  aRange.foreach(println)
  (1 to 5).foreach(x => println("Hello"))

  // lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  val anotherPrepending = 42 +: aList
  val appended = aList :+ 42


  println(prepended)
  println(anotherPrepending)
  println(appended)

  val apples5 = List.fill(5)("apple") // fill is a curried function
  println(apples5)

  println(aList.mkString("-"))

  // arrays (mutable)
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  numbers(3) = 100
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // numbers is an array, here is a conversion
  // implicit conversion ^^
  println(numbersSeq)

  // vectors (default implementation for immutable sequences)
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists

  val maxRuns = 10000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r= new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // list is fast when updating the head or tail (keeps reference to tail)
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk (trie)
  println(getWriteTime(numbersVector))

}
