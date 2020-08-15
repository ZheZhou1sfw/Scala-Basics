package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)

  person.greet("David")
  person.greet()


  val author = new Writer("C Dickens", "Dickens", 1812)
  val imposter = new Writer("C Dickens", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc(10).print
  counter.dec(5).print
}

class Person(name: String, val age: Int) {
  // body
  val x = 2 // fields -- visible to outside

  // == methods ==
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors (not very useful, bc we need to use the default constructor)
  def this(name: String) = this(name, 0)

}

class Writer(firstName: String, surName: String, val year: Int) {
  def fullname(): String = firstName + " " + surName
}



class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge(): Int = yearOfRelease - author.year

  def isWrittenBy(author: Writer):Boolean = this.author == author
  def copy(newYOR: Int): Novel = new Novel(name, newYOR, author)
}


class Counter(val count: Int = 0) {
  def inc = new Counter(count + 1) // immutability
  def dec = new Counter(count - 1)

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n-1)
  }

  def print = println(count)
}







// class parameters are not FIELDS
// add a "val" to convert a class parameter to class field