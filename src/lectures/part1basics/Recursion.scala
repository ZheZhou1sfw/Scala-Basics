package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int  = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }
//  println(factorial(5000))

  // Tail recursive factorial
  // TAIL RECURSION = use the recursive call as the LAST expression
  def anotherFactorial(n: Int): Int = {
    @tailrec
    def factHelper(x: Int, accumulator: Int): Int = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)
    }
    factHelper(n , 1)
  }

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION
  def concatenation(n: Int, str: String): String = {
    @tailrec
    def tailRecConcatenation(t: Int, accu: String): String = {
      if (t == 0) accu
      else tailRecConcatenation(t - 1, accu + str)
    }
    tailRecConcatenation(n, "")
  }
  println(concatenation(4, "hello"))

  def isPrime(n: Int): Boolean = {
//    def isPrimeUntil(t: Int): Boolean = {
//      if (t <= 1) true
//      else n % t != 0 && isPrimeUntil(t - 1)
//    }
    @tailrec
    def tailRecIsPrimeUntil(t: Int, accu: Boolean): Boolean = {
      if (t == 1) accu
      else tailRecIsPrimeUntil(t - 1, accu && (n % t != 0))
    }
    tailRecIsPrimeUntil(n - 1, true)
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37*17))

  def fibo(n: Int): Int = {
    def tailRecFibo(t: Int, last: Int, nextToLast: Int): Int = {
      if (t >= n) last
      else tailRecFibo(t + 1, last + nextToLast, last)
    }
    if (n <= 2) 1
    else tailRecFibo(2, 1, 1)
  }
  println(fibo(2))
  println(fibo(3))
  println(fibo(4))
  println(fibo(5))
  println(fibo(6))
}
