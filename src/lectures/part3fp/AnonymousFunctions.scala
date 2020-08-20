package lectures.part3fp

object AnonymousFunctions extends App {

  val doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  } // equivalent to below
  // anonymous function (Lambda) (syntax sugar)
  val doublerAnon: Int => Int = x => x * 2 // or "val doublerAnon = (x: Int) => x * 2"

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a, b) => a + b

  // no params
  val justDoSomething:() => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambdas (one common style)
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b


  // using anonymous function for previous curried function
  val superAdder = (x: Int) => (y: Int) => x + y
  val adder3 = superAdder(3)
  println(adder3(5)) // 3 + 5
}
