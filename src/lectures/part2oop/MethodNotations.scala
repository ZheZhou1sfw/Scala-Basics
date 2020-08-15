package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, age: Int) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(occupation: String): Person = new Person(s"${this.name} ($occupation)", this.favoriteMovie, this.age)

    def unary_! : String = s"$name, what the hack?!"
    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def learns(thing: String) = s"${this.name} learns $thing"
    def learnsScala = this learns("Scala")

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(times: Int) = s"${this.name} watched ${this.favoriteMovie} $times times"
  }

  val mary = new Person("Mary", "Inception", 22)
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // ^^ infix notation = operator notation (only works for method with single parameter)
  // ^^ an example of syntactic sugar

  // "operators" in Scala
  val tom = new Person("Tom", "Flight Club", 25)
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ?

  // prefix notation (another syntactic sugar)
  val x = -1
  val y = 1.unary_- // equivalent to above ^^
  // unary_ prefix only works with a few operators - + ! ~

  println(!mary)
  println(mary.unary_!)

  // postfix notation (works for methods without parameters) (not very clear)
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent
  println(mary(2))

  println(mary learnsScala)
  println((+mary)(3))
  println((mary + "the yoyo guy")())

}
