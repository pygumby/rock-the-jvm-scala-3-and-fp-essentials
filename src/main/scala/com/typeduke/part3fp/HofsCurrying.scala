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

  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(oneHundred)

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
  }
}
