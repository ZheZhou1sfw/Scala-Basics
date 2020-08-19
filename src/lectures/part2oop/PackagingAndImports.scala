package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  //package members are accessible by their simple name
  val writer = new Writer("John", "hey", 2020)

  // import the package
//  val princess = new Cinderella // equivalent to below
  val princess = new Princess
  val anotherPrincess = new playground.Cinderella // fully qualified name

  // packages are in hierarchy
  // matching folder structure.

  // package object -- specific to Scala
  // calling using package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // When there are duplicate import names:
  // Option 1: Use fully qualified names
  // Option 2: Use aliasing
  val sqlDate = new SqlDate(2018, 5, 4)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
