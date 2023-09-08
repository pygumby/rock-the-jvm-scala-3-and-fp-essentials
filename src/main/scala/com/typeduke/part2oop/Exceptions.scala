package com.typeduke.part2oop

object Exceptions {
  val aString: String = null // `aString.length` crashes with a `NullPointerException`.

  // 1 - `throw` exceptions
  // val aWeirdValue: Int = throw new NullPointerException // returns `Nothing`

  // `Throwable` is superclass to anything that can be `throw`n.
  // |_ `Error`, e.g., `StackOverflowError`, `OutOfMemoryError`, something wrong with the JVM
  // |_ `Exception`, e.g., `NullPointerException`, something wrong with the logic of your program

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No `Int` for you!")
    else 42

  val potentialFail =
    try {
      // Code that may fail
      getInt(true)
    } catch {
      // List most specific cases first, unlike below.
      case e: RuntimeException     => 54
      case e: NullPointerException => 35 // Subtype of `RuntimeException`, will never be reached
      // ...
    } finally {
      // Optional, executed in any case, usually to close resources, no impact on the return value

    }

  // Custom exceptions
  class MyRuntimeException extends RuntimeException {
    override def getMessage(): String = "Very unique message"
  }

  val myException = new MyRuntimeException

  /* Exercise 1
   * Crash with a `StackOverflowException`.
   */

  def soCrash(): Unit = {
    def infinite(): Int = 1 + infinite()

    infinite()
  }

  /* Exercise 2
   * Crash with a `OutOfMemoryException`.
   */

  def oomCrash(): Unit = {
    def bigString(n: Int, acc: String): String =
      if (n == 0) acc
      else bigString(n - 1, acc + acc)

    bigString(1098238420, "Scala")
  }

  def main(args: Array[String]): Unit = {
    println(potentialFail)

    // val throwingMyException = throw myException

    // Exercise 1
    // soCrash()

    // Exercise 2
    // oomCrash()
  }
}
