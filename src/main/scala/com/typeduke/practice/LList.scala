package com.typeduke.practice

import scala.annotation.tailrec

/* Exercise 1
 * LList extension
 *
 * 1. Generic trait `Predicate[T]` with a little method `test(T) => Boolean`
 * 2. Generic trait `Transformer[A, B]` with a method `transform(A) => B`
 * 3. LList:
 *    - `map(transformer) => LList`
 *    - `filter(predicate) => LList`
 *    - `flatMap(transformer from A to LList[B]) => LList[B]`
 *
 * `class EvenPredicate extends Predicate[Int]`
 * `class StringToIntTransformer extends Transformer[String, Int]`
 *
 * `[1, 2, 3].map(n * 2) = [2, 4, 6]`
 * `[1, 2, 3, 4].filter(n % 2) = [2, 4]`
 * `[1, 2, 3].flatMap(n => [n, n + 1]) => (1, 2, 2, 3, 3, 4)`
 */

// Singly linked list
abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element: A): LList[A] = new Cons(element, this)

  infix def ++(other: LList[A]): LList[A]

  def map[B](transformer: Transformer[A, B]): LList[B]
  def filter(predicate: Predicate[A]): LList[A]
  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]
}

class Empty[A] extends LList[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LList[A] = throw new NoSuchElementException
  override def isEmpty: Boolean = true

  override def ++(other: LList[A]): LList[A] = other

  override def map[B](transformer: Transformer[A, B]): LList[B] = new Empty[B]
  override def filter(predicate: Predicate[A]): LList[A] = this
  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = new Empty[B]

  override def toString: String = "[]"
}

class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
  override def isEmpty: Boolean = false

  override def ++(other: LList[A]): LList[A] = 
    new Cons(this.head, this.tail ++ other)

  override def map[B](transformer: Transformer[A, B]): LList[B] =
    new Cons(transformer.transform(this.head), this.tail.map(transformer))

  override def filter(predicate: Predicate[A]): LList[A] =
    if (predicate.test(this.head)) new Cons(this.head, this.tail.filter(predicate))
    else this.tail.filter(predicate)

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] =
    transformer.transform(this.head) ++ this.tail.flatMap(transformer)

  override def toString(): String = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"${this.head}")}]"
  }
}

trait Predicate[T] {
  def test(element: T): Boolean
}

class EvenPredicate extends Predicate[Int] {
  override def test(element: Int): Boolean = element % 2 == 0
}

trait Transformer[A, B] {
  def transform(element: A): B
}

class Doubler extends Transformer[Int, Int] {
  override def transform(element: Int): Int = element * 2
}

class DoublerList extends Transformer[Int, LList[Int]] {
  override def transform(element: Int): LList[Int] =
    new Cons(element, new Cons(element * 2, new Empty))
}

class StringToIntTransformer extends Transformer[String, Int] {
  override def transform(element: String): Int = element.toInt
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = new Empty[Int]

    println(empty)
    println(empty.isEmpty)

    val firstThreeNumbers = new Cons(1, new Cons(2, new Cons(3, empty)))
    val firstThreeNumbers2 = empty.add(1).add(2).add(3)

    println(firstThreeNumbers)
    println(firstThreeNumbers2)
    println(firstThreeNumbers2.isEmpty)

    val someStrings = new Cons("dog", new Cons("cat", new Empty))

    println(someStrings)

    // `map` tests
    val numbersDoubled = firstThreeNumbers.map(new Doubler)
    println(numbersDoubled)
    val numbersNested = firstThreeNumbers.map(new DoublerList)
    println(numbersNested)

    // `filter` tests
    val onlyEvenNumbers = firstThreeNumbers.filter(new EvenPredicate)
    println(onlyEvenNumbers)

    // `concatenation` tests
    val listInBothWays = firstThreeNumbers ++ firstThreeNumbers2
    println(listInBothWays)

    // `flatMap` tests
    val flattenedList = firstThreeNumbers.flatMap(new DoublerList)
    println(flattenedList)
  }
}
