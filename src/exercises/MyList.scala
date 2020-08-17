package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  override def toString: String = "[" + printElements + "]"

  def ++[B >: A](list: MyList[B]): MyList[B]
}

// Generic trait MyPredicate[T]
trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

// Generic trait MyTransformer[A, B]
trait MyTransformer[-A, B] {
  def transform(elem:A): B
}



// object can extend classes
object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons[Int](2, new Cons[Int](4, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons[Int](5, new Cons[Int](6, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons[String]("Scala", Empty))

  println(listOfIntegers).toString
  println(listOfStrings).toString

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 3
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers))
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons[Int](elem, new Cons(elem + 1, Empty))
  }))
}