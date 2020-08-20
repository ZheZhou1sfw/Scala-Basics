package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) // calls the apply method

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS





  // a function which takes 2 strings and concatenate them
  val concatStrings = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concatStrings("123", "456"));

  // a function takes an int and returns another function which takes an int and returns an int
  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(5)) // 3 + 5

  // a curried function
  println(superAdder(3)(4)) // equivalent to above two lines
}

trait MyFunction[A, B] {
  def apply(element: A): B
}

