package com.typeduke.part2oop

import scala.language.postfixOps

object MethodNotation {
  class Person(val name: String, val age: Int, favoriteMovie: String) {
    // Infix modifier, only available for methods with one parameter
    infix def likes(movie: String): Boolean =
      movie == this.favoriteMovie

    infix def +(other: Person): String =
      s"${this.name} is hanging out with ${other.name}."

    infix def +(nickname: String): Person =
      new Person(s"${this.name} (${nickname})", this.age, this.favoriteMovie)

    infix def !!(progLang: String): String = // ?, !, >>, <+>
      s"How can ${progLang} be so cool?"

    // Prefix
    // Supported unary operators: +, -, ~, !
    def unary_- : String =
      s"${this.name}'s alter ego"

    def unary_+ : Person =
      new Person(this.name, this.age + 1, this.favoriteMovie)

    // Postfix, only available for methods with no parameters
    // DISCOURAGED!
    // Deprecated since Scala 2.13
    def isAlive: Boolean =
      true

    // apply
    def apply(): String =
      s"Hi, my name is ${this.name} and I really enjoy ${this.favoriteMovie}."

    def apply(n: Int): String =
      s"${this.name} watched ${this.favoriteMovie} $n times."
  }

  val mary = new Person("Mary", 34, "Inception")
  val john = new Person("John", 36, "Fight Club")

  /* 1) New methods for class Person

    - A `+` method that returns that person with a nickname
      E.g., `mary + "the rockstar` = new Person("mary the rockstar", _, _)
    - A unary `+` method that increases the person's age
      E.g., `+mary` = new Person(_, _, age + 1)
    - An `apply` method with an Int argument
      E.g., `mary(2)` = "Mary watched Inception 2 times"
   */

  def main(args: Array[String]): Unit = {
    // Infix notation
    println(mary.likes("Fight Club"))
    println(mary likes "Fight Club") // Identical

    // "Operator" style
    println(mary + john)
    println(mary.+(john)) // Identical

    // Operators in Scala are just plain methods!
    println(2 + 3)
    println(2.+(3)) // Identical

    println(mary !! "Scala")

    // Prefix notation
    println(-mary)
    println(mary.unary_-) // Identical

    // Postfix notation
    println(mary.isAlive)
    println(mary isAlive)

    // apply
    println(mary.apply())
    println(mary())

    // Exercise 1
    println((mary + "the rockstar").name)
    println((+mary).age)
    println(mary(10))
  }
}
