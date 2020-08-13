package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x = 42
  println(x)
  // things defined by val cannot be reassigned
  // val is immutable

  // COMPILER can infer types
  val aString: String = "hello"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 4654156578915L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.1415926

  // variables
  var aVaribable: Int = 4

  aVaribable = 5

}
