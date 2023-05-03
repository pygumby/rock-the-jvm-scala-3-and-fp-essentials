package com.typeduke.part1basics

object Expressions {
  // Expressions are structures that can be evaluated to a value.
  val meaningOfLife = 40 + 2

  // Mathematical expressions: +, -, *, /, |, &, <<, >>, >>>
  val mathExpression = 2 + 3 * 4

  // Comparison expressions: <, <=, >, >=, ==, !=
  val equalityTest = 1 == 2

  // Boolean expressions: !, ||, &&
  val notEqualityTest = !equalityTest

  // Instructions vs. expressions
  // Instructions are executed, expressions are evaluated.
  // We think in terms of expressions!

  // `if`s are expressions.
  val aCondition = true
  val anIfExpression = if (aCondition) 45 else 99

  // Code blocks are expressions.
  val aCodeBlock = {
    val localValue = 78
    // Last expression = value of the block
    localValue + 54
  }

  // Everything is an expression...

  // Exercises
  // Without running the code, what do you think these values will print?

  // 1) `true`

  val someValue = {
    2 < 3
  }

  // 2) `42`

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }

  // 3) `Scala`, followed by `()` (the one possible value of the Unit type)

  val yetAnotherValue =
    println("Scala")

  def main(args: Array[String]): Unit = {
    println(someValue)
    println(someOtherValue)
    println(yetAnotherValue)
  }
}
