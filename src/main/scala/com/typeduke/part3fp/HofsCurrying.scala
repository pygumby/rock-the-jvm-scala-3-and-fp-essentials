package com.typeduke.part3fp

import scala.annotation.tailrec

object HofsCurrying {
  // Higher-order functions (HOFs)
  val aHof: (Int, (Int => Int)) => Int = (x, fun) => x + 1
  val anotherHof: Int => (Int => Int) = x => (y => y + 2 * x)

  // Quick exercise
  val superFun: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (x, fun) => y => x + y

  // Examples: map, flatMap, filter

  // More examples
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)

  def nTimes2(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) x => x
    else x => nTimes2(f, n - 1)(f(x))

  val plusOneHundred = nTimes2(plusOne, 100)
  val oneHundred = plusOneHundred(0)

  // Currying = HOFs returning function instances
  val superAdder: Int => Int => Int = x => y => x + y
  val addThree: Int => Int = superAdder(3)
  val invokedSuperAdder = superAdder(100) // 103

  // Currying is also available for methods
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // x => "%4.2f".format(x)
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f") // x => "%10.8f".format(x)

  /* Exercise 1
   * Create functions that apply and un-apply currying to a given function.
   *
   * toCurry(f: (Int, Int) => Int): Int => Int => Int
   * fromCurry(f: Int => Int => Int): (Int, Int) => Int
   */

  def toCurry[A, B, C](f: (A, B) => C): A => B => C =
    x => y => f(x, y)

  val superAdder2 = toCurry[Int, Int, Int](_ + _) // equivalent to `superAdder`

  def fromCurry[A, B, C](f: A => B => C): (A, B) => C =
    (x, y) => f(x)(y)

  val simpleAdder = fromCurry(superAdder2)

  /* Exercise 2
   * Create a functions that compose given functions.
   *
   * compose(f, g): x => f(g(x))
   * andThen(f, g): x => g(f(x))
   */

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))

  val incrementer = (x: Int) => x + 1
  val doubler = (x: Int) => 2 * x

  val composedApplication = compose(incrementer, doubler)

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  val sequencedApplication = andThen(incrementer, doubler)

  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(oneHundred)

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))

    // Exercise 1
    println(superAdder2(40)(2)) // 42
    println(simpleAdder(40, 2)) // 42

    // Exercise 2
    println(composedApplication(42)) // 85
    println(sequencedApplication(42)) // 86
  }
}
