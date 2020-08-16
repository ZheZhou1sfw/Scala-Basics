package lectures.part2oop

object Objects {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { // type + its only instance
    // "static"/"class" - level funcctionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method (using apply)
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS ^^^

  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john)

    val bobbie = Person(mary, john) // apply method
  }
  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
  // Or the object needs to extend the App object
}
