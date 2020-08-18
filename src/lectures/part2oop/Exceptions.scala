package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length)
  // ^^^ this will crash with a NPE

  // throwing exceptions

//  val aWeirdValue = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // *use finally only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
//  throw new MyException

  // OutOfMemoryError
//  val array = Array.ofDim(Int.MaxValue)

  // StackOverflowError
//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  object PocketCalculator {

    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) =
      if (y == 0) throw new MathCalculationException
      else x / y
  }
}
