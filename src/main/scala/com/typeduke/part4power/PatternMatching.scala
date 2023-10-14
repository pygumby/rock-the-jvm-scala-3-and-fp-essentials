package com.typeduke.part4power

import scala.util.Random

object PatternMatching {
  // Pattern matching = `switch` on steroids
  val random = new Random()
  val aValue = random.nextInt(100)

  val description = aValue match {
    case 1 => "The first"
    case 2 => "The second"
    case 3 => "The third"
    case _ => s"Something else: $aValue" // If missing, a `MatchError` may be thrown.
  }

  // Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 18 => s"Hi there, my name is $n and I'm $a years old." // `if` guard
    case Person(n, _)           => s"Hi there, my name is $n and I won't say my age."
    // case _ => "I don't know who I am." // Unreachable, hence commented out
  }

  // Tips:
  // - Patterns are matched in order, so put the most specific ones first.
  // - If no patterns match, there will be a `MatchError`.
  // - Return type is the lowest common ancestor of all types on the right-hand side of each branch.

  // Pattern matching on `sealed` hierarchies
  sealed trait Animal
  case class Dog(breed: String) extends Animal
  case class Cat(meowStyle: String) extends Animal

  val anAnimal: Animal = Dog("Terra Nova")
  val animalMatching = anAnimal match {
    case Dog(_) => "Detected a dog"
    case Cat(_) => "Detected a cat" // Compiler warning if missing: "match may not be exhaustive."
  }

  /* Exercise 1
   * Interpret `Expr`s into strings.
   */

  sealed trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n)   => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParens(expr: Expr): String = expr match {
        case Sum(_, _) => s"(${show(expr)})"
        case _         => show(expr)
      }
      maybeShowParens(e1) + " * " + maybeShowParens(e2)
    }
  }

  def main(args: Array[String]): Unit = {
    println(description)
    println(greeting)
    println(show(Sum(Number(2), Number(3)))) // "2 + 3"
    println(show(Sum(Sum(Number(2), Number(3)), Number(4)))) // "2 + 3 + 4"
    println(show(Prod(Sum(Number(2), Number(3)), Number(4)))) // "(2 + 3) * 4"
    println(show(Sum(Prod(Number(2), Number(3)), Number(4)))) // "2 * 3 + 4"
  }
}
