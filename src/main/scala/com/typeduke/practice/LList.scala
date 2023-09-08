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

/* Exercise 2
 * Case classes
 *
 * Find out where case classes make sense for `LList` and apply the respective changes.
 */

/* Exercise 3
 * Implement a `find` method.
 */

// Singly linked list
abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element: A): LList[A] = Cons(element, this)

  infix def ++(other: LList[A]): LList[A]

  def map[B](transformer: Transformer[A, B]): LList[B]
  def filter(predicate: Predicate[A]): LList[A]
  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LList[A] = throw new NoSuchElementException
  override def isEmpty: Boolean = true

  override def ++(other: LList[A]): LList[A] = other

  override def map[B](transformer: Transformer[A, B]): LList[B] = Empty()
  override def filter(predicate: Predicate[A]): LList[A] = this
  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = Empty()

  override def toString: String = "[]"
}

case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
  override def isEmpty: Boolean = false

  override def ++(other: LList[A]): LList[A] =
    Cons(this.head, this.tail ++ other)

  override def map[B](transformer: Transformer[A, B]): LList[B] =
    Cons(transformer.transform(this.head), this.tail.map(transformer))

  override def filter(predicate: Predicate[A]): LList[A] =
    if (predicate.test(this.head)) Cons(this.head, this.tail.filter(predicate))
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

object LList {
  def find[A](list: LList[A], predicate: Predicate[A]): A =
    if (list.isEmpty) throw new NoSuchElementException
    else if (predicate.test(list.head)) list.head
    else find(list.tail, predicate)
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
    Cons(element, Cons(element * 2, Empty()))
}

class StringToIntTransformer extends Transformer[String, Int] {
  override def transform(element: String): Int = element.toInt
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = Empty[Int]()

    println(empty)
    println(empty.isEmpty)

    val firstThreeNumbers = Cons(1, Cons(2, Cons(3, empty)))
    val firstThreeNumbers2 = empty.add(1).add(2).add(3)

    println(firstThreeNumbers)
    println(firstThreeNumbers2)
    println(firstThreeNumbers2.isEmpty)

    val someStrings = Cons("dog", Cons("cat", Empty()))

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

    // `find` tests
    println(LList.find(firstThreeNumbers, new EvenPredicate)) // 2
    // Should throw a `NoSuchElementException`
    // println(LList.find(firstThreeNumbers, new Predicate[Int] {
    //   override def test(element: Int): Boolean = element > 5
    // }))
  }
}
