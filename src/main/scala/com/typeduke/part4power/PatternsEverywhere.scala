package com.typeduke.part4power

object PatternsEverywhere {
  // Big idea #1: Catches are actually matches
  val potentialFailure =
    try {
      // Some code
    } catch {
      case _: RuntimeException     => "Runtime exception"
      case _: NullPointerException => "Null pointer exception"
      case _: Exception            => "Some other exception"
    }

  // Big idea #2: `for` comprehension generators are beased on pattern matching
  val aList = List(1, 2, 3, 4)
  val evenNumbers = for {
    n <- aList if n % 2 == 0
  } yield 10 * n

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples if first < 3
  } yield second * 100

  // Big idea #3: New syntax (very Python-like)
  val aTuple = (1, 2, 3)
  val (a, b, c) = aTuple

  val head :: tail = tuples: @unchecked

  def main(args: Array[String]): Unit = {}
}
