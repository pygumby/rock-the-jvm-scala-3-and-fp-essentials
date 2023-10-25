package com.typeduke.part4power

object ImperativeProgramming {
  val meaningOfLife: Int = 42

  var aVariable = 99
  aVariable = 100 // Variables can be reassigned.

  aVariable += 10 // Same as `aVariable = aVariable + 10`

  // The `++` operator does not exist in Scala.
  // `aVariable++` doesn't compile.

  // Loops
  def testLoops(): Unit = {
    var i = 0
    while (i < 10) {
      println(s"Counter at $i")
      i += 1
    }
  }

  // Imperative programming (loops/variables/mutable data) are not recommended:
  // - Hard to read and understand, especially in growing code bases
  // - Vulnerable to concurrency problems, e.g., need for synchronization
  
  // Imperative programming can help in certain situations:
  // - Performance-critical applications (0.1% of cases)
  // - Interactions with Java libraries (usually mutable)

  // Using imperative programming in Scala for no good reason defeats the purpose of Scala.

  // Imperative expressions usually are of type `Unit`.
  val anExpression: Unit = aVariable += 10
  val anotherExpression: Unit = while (aVariable < 13) {
    println("Counting more")
    aVariable += 1
  }

  def main(args: Array[String]): Unit = {
    testLoops()
  }
}
