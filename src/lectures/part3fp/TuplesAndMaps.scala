package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String) <- Syntax sugar for tuple types

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // "hello, Scala", 2

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is syntax sugar for tuple (a,b)
  println(phonebook)

  // map operations
  println(phonebook.contains("Jim")) // true
  println(phonebook("Jim")) // return 555
//  println(phonebook("hehehe")) // exception without (withDefaultValue(xxx))

  // add a pairing
  val newPairing = "Mary" -> 678 // tuple
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter  --- takes a pairing

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(phonebook.view.mapValues(number => number * 10).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  // groupBy
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

}
