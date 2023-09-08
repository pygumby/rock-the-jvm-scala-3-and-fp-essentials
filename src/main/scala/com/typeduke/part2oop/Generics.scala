package com.typeduke.part2oop

object Generics {
  // Generic list - reuse code on different types
  abstract class MyList[A] {
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  val listOfIntegers: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))

  val firstNumber = listOfIntegers.head
  val sum = firstNumber + 3

  // Multiple generic types
  trait Map[K, V]

  // Generic methods
  object MyList {
    def fromTwoElements[A](elem1: A, elem2: A): MyList[A] =
      new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
  }

  // Type inference
  val firstTwoNumbers = MyList.fromTwoElements[Int](1, 2)
  val firstTwoNumbers2 = MyList.fromTwoElements(1, 2)
  val firstTwoNumbers3 = new NonEmpty(1, new NonEmpty(2, new Empty))

  def main(args: Array[String]): Unit = {}
}
