package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // what "case" does:
  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString method
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. Case classes have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. Case classes have companion objects
  val thePersonObject = Person
  println(Person)
  val mary = Person("Mary", 23) // using apply method from the person object, no need to use keyword "new" for constructors

  // 6. Case classes are serializable (for distributed systems)
  // Akka important

  // 7. Case classes have extractor patterns = Case classes can be used in Pattern matching

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}
