package com.typeduke.part2oop

object OOBasics {
  // Class
  class Person(val name: String, age: Int) { // Constructor signature
    // Auxiliary constructors
    // Use of aux ctors in Scala is rare due as they can really only call other ctors.
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

    // Exercise 1
    val charlesDickens = new Writer("Charles", "Dickens", 1812)
    val charlesDickensImpostor = new Writer("Charles", "Dickens", 2021)
    val novel = new Novel("Great Expectations", 1861, charlesDickens)
    val newEdition = novel.copy(1871)
    println(charlesDickens.fullName)
    println(novel.authorAge)
    println(novel.isWrittenBy(charlesDickensImpostor))
    println(novel.isWrittenBy(charlesDickens))
    println(newEdition.authorAge)

    // Exercise 2
    val counter = new Counter()
    counter.print()
    counter.increment().print()
    counter.increment() // `increment()` always returns new instances,...
    counter.print() // ...hence, the original instance will keep printing `0`.
    counter.increment(10).print()
  }
}

/* Exercise 1
 * Novel and Writer classes
 *
 * Imagine we're creating a backend for a book publishing house.
 * Create a Novel and a Writer class.
 *
 * Writer:
 * - Fields:
 *   - first name
 *   - surname
 *   - year
 * - Methods:
 *   - full name
 *
 * Novel:
 * - Fields:
 *   - name
 *   - year of release
 *   - author
 * - Methods:
 *   - author age
 *   - is written by (author)
 *   - copy (new year of release) = new instance of Novel
 */

class Writer(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def fullName: String = s"${this.firstName} ${this.lastName}"
}

class Novel(val title: String, val yearOfRelease: Int, val author: Writer) {
  def authorAge: Int =
    this.yearOfRelease - author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean =
    this.author == author

  def copy(newYear: Int): Novel =
    new Novel(title, newYear, author)
}

/* Exercise 2
 * Immutable counter class
 *
 * Create an immutable counter class with the following requirements:
 *
 * - Constructed with an initial count
 * - increment/decrement = new instance of counter
 * - increment(n)/decrement(n) = new instance of counter
 * - print()
 *
 * Benefits of immutable data:
 * - Works very well in distributed environments
 * - Results in easier to read and understand code
 */

class Counter(val count: Int = 0) {
  def increment(): Counter =
    new Counter(this.count + 1)

  def decrement(): Counter =
    if (this.count == 0) this
    else new Counter(this.count - 1)

  def increment(n: Int): Counter =
    if (n <= 0) this
    else this.increment().increment(n - 1) // Volunerable to stack overflow errors

  def decrement(n: Int): Counter =
    if (n <= 0) this
    else this.decrement().decrement(n - 1)

  def print(): Unit =
    println(s"Current count: ${this.count}")
}
