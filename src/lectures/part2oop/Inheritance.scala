package lectures.part2oop

object Inheritance extends App {
  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
    // private def eat = println("nomnom")
  }

  // single class inheritance (extend one class only)
  // subclass / superclass
  class Cat extends Animal

  val cat = new Cat
  cat.eat

  // constructors
  class Person(name: String, age: Int) {
//    // auxiliary constructor
//    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // Overriding
  class Dog(override val creatureType: String = "domestic") extends Animal {
//    override val creatureType: String = "domestic"
    override def eat = {
      super.eat
      println("wofwof")
    }
  }
  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // always go the most overridden version as possible

  // overRiding vs overLoading

  // super -> reference a method or a field from parent class

  // preventing overrides
  // 1 - use keyword "final" on member(methods/ val), preventing from overriding
  // 2 - use keyword "final" on the class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other class
    // ^^ keyword "sealed" on class
}
