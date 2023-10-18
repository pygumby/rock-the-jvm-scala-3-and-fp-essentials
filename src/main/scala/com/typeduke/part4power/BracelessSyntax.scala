package com.typeduke.part4power

object BracelessSyntax {
  // `if` expressions, Scala 2 compatible
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  val anIfExpression2 = if (2 > 3) {
    "bigger"
  } else {
    "smaller"
  }

  val anIfExpression3 =
    if (2 > 3) "bigger"
    else "smaller"

  // `if` expressons, Scala 3's braceless syntax
  val anIfExpressionNew =
    if 2 > 3 then // Let go of the parens and braces, thanks to `then` keyword
      "bigger" // Must have higher intentation than the `if` line
    else "smaller"

  val anIfExpressionNew2 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  val anIfExpressionNew3 =
    if 2 > 3 then "bigger" else "smaller"

  // `for` comprehensions, Scala 2 compatible
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // `for` comprehensions, Scala 3's braceless syntax
  val aForComprehensionNew = for
    n <- List(1, 2, 3)
    s <- List("black", "white")
  yield s"$n$s"

  // Pattern matching, Scala 2 compatible
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "The one"
    case 2 => "Double or nothing"
    case _ => "Something else"
  }

  // Pattern matching, Scala 3's braceless syntax
  val aPatternMatchNew = meaningOfLife match
    case 1 => "The one"
    case 2 => "Double or nothing"
    case _ => "Something else"

  // Tip: Stay consistent!
  // Don't mix code with and without braces!

  // Methods, Scala 2 compatible
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40

    // Lots of newlines permitted

    partialResult + 2
  }

  // Methods, Scala 3's braceless syntax
  def computeMeaningOfLifeNew(arg: Int): Int =
    val partialResult = 40

    // Lots of newlines permitted

    partialResult + 2

  // Classes, Scala 3's braceless syntax (same for traits, objects, enums, etc.)
  class Animal: // `:` denotes that the compiler expects the body of the class.
    def eat(): Unit =
      println("I'm eating.")

    def grow(): Unit =
      println("I'm growing.")

    // 3000 more lines of code here would make the optional `end` token sensible:
  end Animal

  // An `end` token can be used "pretty much anywhere else with significant indentation".
  // It's recommended to use it for "everything that has more than four lines of code".

  // Anonymous classes, Scala 3's braceless syntax
  val aSpecialAnimal = new Animal: // `:` is mandatory here.
    override def eat(): Unit =
      println("I'm special!")

  // How the compiler interprets indentation: strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab

  // Don't mix spaces and tabs:
  // 3 tabs + 2 spaces ? 2 tabs + 3 spaces

  def main(args: Array[String]): Unit = {
    println(anIfExpressionNew2)
    println(computeMeaningOfLifeNew(78))
  }
}
