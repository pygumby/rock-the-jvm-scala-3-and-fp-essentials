package com.typeduke.part2oop

object OOBasics {
  // Class
  class Person(val name: String, age: Int) { // Constructor signature
    // Auxiliary constructors
    // Use of aux ctors in Scala is rare due to the limitation that they can really only call other ctors.
    // Essentially, the same behavior can be achieved using default args.
    def this(name: String) =
      this(name, 0)

    def this() =
      this("Jane Doe")

    // Fields
    val allCaps = name.toUpperCase()

    // Methods
    def greet(name: String): String =
      s"${this.name} says: Hi, $name!"

    // Overloading
    // This method's signature differs from that of the previous method.
    def greet(): String =
      s"Hi everyone, my name is $name!"
  }

  val aPerson: Person = new Person("John", 26)
  val john = aPerson.name // Constructor parameter != field
  val johnSaysHiToDaniel = aPerson.greet("Daniel")
  val johnSaysHi = aPerson.greet()

  val genericPerson: Person = new Person()

  def main(args: Array[String]): Unit = {
    println(johnSaysHiToDaniel)
    println(johnSaysHi)

    println(genericPerson.greet())
  }
}
