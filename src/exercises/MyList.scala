package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String

  // higher-order functions: either receive functions as params, or return functions as results
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  override def toString: String = "[" + printElements + "]"

  def ++[B >: A](list: MyList[B]): MyList[B]
}




// object can extend classes
case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons[Int](2, new Cons[Int](4, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons[Int](5, new Cons[Int](6, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons[String]("Scala", Empty))

  println(listOfIntegers).toString
  println(listOfStrings).toString

  println(listOfIntegers.map((elem: Int) => elem * 3).toString)

  println(listOfIntegers.filter((elem: Int) => elem % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers))
  println(listOfIntegers.flatMap((elem: Int) => new Cons[Int](elem, new Cons(elem + 1, Empty))))
}