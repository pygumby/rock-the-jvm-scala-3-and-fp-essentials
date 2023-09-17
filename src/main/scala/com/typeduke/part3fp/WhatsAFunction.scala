package com.typeduke.part3fp

object WhatsAFunction {
  // Functional programming (FP): Functions are first-class citizens.
  // Scala enables this on top of the JVM, which is structured around interfaces and classes.

  // The below is equivalent to Scala's `Function1` type:
  trait MyFunction[A, B] {
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler(meaningOfLife) // identical to `doubler.apply(meaningOfLife)`

  // Function types
  val doublerStandard = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningDoubled2 = doublerStandard(meaningOfLife)

  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  val anAddition = adder(2, 67)

  // `(Int, String, Double, Boolean) => Int` equals `Function4[Int, String, Double, Boolean, Int]`
  val aThreeParamsFunction = new Function4[Int, String, Double, Boolean, Int] {
    override def apply(v1: Int, v2: String, v3: Double, v4: Boolean): Int = ???
  }

  // All functions are instances of `FunctionX` with `apply` methods.

  /* Exercise 1
   * Write a function which takes two strings and concatenates them.
   */

  val concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String) = a + b
  }

  /* Exercise 2
   * Write a function which takes an `Int` and returns another function.
   */

  val superAdder: Int => Int => Int = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  // Currying
  val addTwo = superAdder(2)
  val anAddition2 = addTwo(40)
  val anAddition3 = superAdder(40)(2)

  // Function values != methods

  def main(args: Array[String]): Unit = {
    // Exercise 1
    println(concatenator("I love ", "Scala"))

    // Exercise 2
    println(anAddition2)
    println(anAddition3)
  }
}
