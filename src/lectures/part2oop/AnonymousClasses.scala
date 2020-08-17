package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }


  // anonymous class (here we are first create a anonymous class, then instantiate from that class)
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahahahahaahahah")
  }


  /*
    Equivalent with below:

    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahahahahahahaahahah")
    }
    val funnyAnimal: Animal = new AnonymousClasses$$anon$1

   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  // extends and instantiate a non-abstract class on the fly (creating an anonymous class)
  val jim = new Person("jim") {
    override def sayHi: Unit = println("nom nom nom")
  }

  println(jim.getClass)
  

}
