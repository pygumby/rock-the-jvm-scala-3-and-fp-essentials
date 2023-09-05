package com.typeduke.part2oop

object CaseClasses {
  // Case classes = lightweight data structures
  case class Person(name: String, age: Int) {
    // You can do here whatever you can do in a regular class.
  }

  // 1 - Class arguments are now fields
  val daniel = new Person("Daniel", 99)
  val danielsAge = daniel.age

  // 2 - `toString`, `equals` and `hashCode` (implemented in terms of fields)
  val danielToString = daniel.toString // "Person("Daniel", 99)"
  val danielDuped = new Person("Daniel", 99)
  val isSameDaniel = daniel == danielDuped // true
  
  // 3 - Utility methods
  val danielYounger = daniel.copy(age = 78)

  // 4 - Case classes have companion objects.
  val thePersonSingleton = Person
  val daniel2 = Person("Daniel", 99) // equivalent to `Person.apply("Daniel", 99)`

  // 5 - Case classes are serializable.
  // Exemplary use case: Akka

  // 6 - Case classes have extractor patterns.
  // ...for pattern matching, which we'll discuss later.

  // Case classes can't be defined without a parameters list.
  // case class CCWithNoParamsList
  // val ccnpl = new CCWithNoParamsList
  // val ccnpl2 = new CCWithNoParamsList // All instances would be equal!

  // Case classes can be defined without parameters.
  case class CCWithNoParams[A]() // This makes sense mainly in the context of generics.
  val ccnp = new CCWithNoParams()
  val ccnp2 = new CCWithNoParams() // These would still be equal.

  // Case objects
  case object UnitedKingdom {
    // Fields and methods
    def name: String = "The UK of GB and NI"
  }

  def main(args: Array[String]): Unit = {
    println("Hello")
    println(danielsAge)
  }
}
