package com.typeduke.part1basics

import scala.annotation.tailrec

object Recursion {
  // "Repetition" = recursion
  def sumUntil(n: Int): Int =
    if (n <= 0) 0
    else n + sumUntil(n - 1) // Stack recursion

  def sumUntilV2(n: Int): Int = {
    @tailrec
    def sumUntilTailRec(x: Int, acc: Int): Int =
      if (x <= 0) acc
      else sumUntilTailRec(x - 1, acc + x)
      // Tail recursion = recursive call occurs LAST in its code path.
      // No further stack frames are necessary = no more risk of stack overflows

    sumUntilTailRec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b)

  def sumNumbersBetweenV2(a: Int, b: Int): Int = {
    @tailrec
    def sumNumbersBetweenTailRec(currentNumber: Int, accumulator: Int): Int =
      if (currentNumber > b) accumulator
      else sumNumbersBetweenTailRec(currentNumber + 1, currentNumber + accumulator)

    sumNumbersBetweenTailRec(a, 0)
  }

  /* Exercise 1
   * Concatenate a string n times
   */

  def concat(str: String, n: Int): String = {
    @tailrec
    def concatTailRec(remainingTimes: Int, acc: String): String =
      if (remainingTimes <= 0) acc
      else concatTailRec(remainingTimes - 1, str + acc)

    concatTailRec(n, "")
  }

  /* Exercise 2
   * Fibonacci function, tail recursive
   */

  def fib(n: Int): Int = {
    def fibTailRec(i: Int, last: Int, prev: Int): Int =
      if (i >= n) last
      else fibTailRec(i + 1, last + prev, last)

    if (n <= 2) 1
    else fibTailRec(2, 1, 1)
  }

  /* Exercise 3
   * Is the isPrime function tail recursive or not?
   */

  // Yes, because the `&&` operation in the helper function's `else` branch is short-circuiting.
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    println(sumUntil(3))
    println(sumUntilV2(3))
    println(sumNumbersBetween(1, 4))
    println(sumNumbersBetweenV2(1, 4))

    println(concat("Scala", 5))
    println(fib(7))
  }
}
