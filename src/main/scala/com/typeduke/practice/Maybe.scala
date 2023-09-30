package com.typeduke.practice

/* Exercise 1
 * Define `Maybe[A]`, a collection of at most one element.
 *
 * Also define the following methods:
 * - `map`
 * - `flatMap`
 * - `filter`
 */

abstract class Maybe[A] {
  def map[B](f: A => B): Maybe[B]
  def flatMap[B](f: A => Maybe[B]): Maybe[B]
  def filter(predicate: A => Boolean): Maybe[A]
}

case class MaybeNot[A]() extends Maybe[A] {
  override def map[B](f: A => B): Maybe[B] = MaybeNot[B]()
  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = MaybeNot[B]()
  override def filter(predicate: A => Boolean): Maybe[A] = this
}

case class Just[A](val value: A) extends Maybe[A] {
  override def map[B](f: A => B): Maybe[B] = Just(f(this.value))

  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(value)

  override def filter(predicate: A => Boolean): Maybe[A] =
    if (predicate(this.value)) this
    else MaybeNot()
}

object MaybeTest {
  def main(args: Array[String]): Unit = {
    // Exercise 1
    val maybeInt: Maybe[Int] = Just(3)
    val maybeInt2: Maybe[Int] = MaybeNot()
    val maybeIncrementedInt: Maybe[Int] = maybeInt.map(_ + 1)
    val maybeIncrementedInt2: Maybe[Int] = maybeInt2.map(_ + 1)
    println(maybeIncrementedInt) // `Just(4)`
    println(maybeIncrementedInt2) // `MaybeNot()`

    val maybeFiltered = maybeInt.filter(_ % 2 == 0)
    println(maybeFiltered) // `MaybeNot()`

    val maybeFlatMapped = maybeInt.flatMap(number => Just(number * 3))
    println(maybeFlatMapped) // `Just(9)`
  }
}
