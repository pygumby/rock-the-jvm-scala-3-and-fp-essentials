package com.typeduke.part3fp

object TuplesMaps {
  // Tuples = finite, ordered "lists" (not actually lists, as they can be heterogeneous)
  val aTuple: (Int, String) = (2, "Rock the JVM") // `Tuple2[Int, String]` == `(Int, String)`
  val firstField = aTuple._1
  val aCopiedTuple = aTuple.copy(_1 = 54)

  // Syntactic sugar for tuples of two elements
  val aTuple2 = 2 -> "Rock the JVM" // `(2, "Rock the JVM")` == `2 -> "Rock the JVM"`

  // Maps = associations between keys and values
  val aMap = Map()
  val phoneBook: Map[String, Int] = Map(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  ).withDefaultValue(-1)

  // `Map`'s Core APIs
  val phoneBookHasDaniel = phoneBook.contains("Daniel")
  val marysPhoneNumber = phoneBook("Mary") // would throw an exception, hadn't we set a default

  // Add a pair
  val newPair = "Mary" -> 678
  val newPhoneBook = phoneBook + newPair

  // Remove a key
  val phoneBookWithoutDaniel = phoneBook - "Daniel" // This will also return a new map.

  // From `List` to `Map`
  val linearPhoneBook = List(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  )
  val phoneBook2 = linearPhoneBook.toMap

  // From `Map` to `List`
  val linearPhoneBook2 = linearPhoneBook.toList // `toSeq`, `toVector`, `toArray`, `toSet`, ...

  // `map`, `flatMap`, `filter`
  // Be careful with the below, `Map("Jim" -> 42, "jim" -> 13)` would result in an undefined value.
  val aProcessedPhoneBook = phoneBook.map(pair => (pair._1.toUpperCase(), pair._2))

  // Filtering keys
  val noJs = phoneBook.view.filterKeys(!_.startsWith("J")).toMap

  // Mapping values
  val prefixNumbers = phoneBook.view.mapValues(number => s"0255-$number").toMap

  // Other collections create maps.
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  val nameGroupings = names.groupBy(name => name.charAt(0)) // `Map[Char, List[String]]`

  def main(args: Array[String]): Unit = {
    println(phoneBook)
    println(phoneBookHasDaniel)
    println(marysPhoneNumber)
    println(nameGroupings)
  }
}
