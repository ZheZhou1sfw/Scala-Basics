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

  // what happens if two keys become the same after some operation? ans: overlapped keys will disappear
  // !!! ^^^ careful with mapping keys

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsOfA = network(a)
    val friendsOfB = network(b)

    network + (a -> (friendsOfA + b)) + (b -> (friendsOfB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsOfA = network(a)
    val friendsOfB = network(b)

    network + (a -> (friendsOfA - b)) + (b -> (friendsOfB - a))
  }

  def remove(network: Map[String, Set[String]], person: String):  Map[String, Set[String]] = {

    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Mary"), "Bob")
  println(network)
  println(friend(network, "Bob", "Mary"))

  println(unfriend(friend(network, "Bob", "Mary"), "Mary", "Bob"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))


  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val maryJimBob = friend(jimBob, "Bob", "Mary")

  println(maryJimBob)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(maryJimBob, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(maryJimBob))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(pair => pair._2.isEmpty)

  println(nPeopleWithNoFriends(maryJimBob))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {

    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail)
      }


    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(maryJimBob, "Mary", "Jim")) // false
  println(socialConnection(maryJimBob, "Mary", "Bob")) // true
}
