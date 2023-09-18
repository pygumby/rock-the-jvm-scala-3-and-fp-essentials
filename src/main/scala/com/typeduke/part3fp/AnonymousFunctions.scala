package com.typeduke.part3fp

object AnonymousFunctions {
  // Instances of FunctionN
  val doubler: Int => Int = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // Lambdas = anonymous function instances
  val doubler2: Int => Int = (x: Int) => x * 2 // equivalent to `doubler`
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y // equivalent to `new Function2`

  // Zero-parameters functions
  val justDoSomething: () => Int = () => 45
  val anInvocation = justDoSomething()

  // Alt syntax with curly braces
  // Having the `{` after the `=>` works too, but is considered "less handy" when passing functions.
  val stringToInt = { (s: String) =>
    // Implementation: code block
    s.toInt
  }

  // Type inference
  // If I specify the type of a value, e.g., `Int => Int`, I can leave the parameter type blank.
  val doubler3: Int => Int = x => x * 2
  val adder2: (Int, Int) => Int = (x, y) => x + y

  // "Shortest lambdas"
  // Each underscore refers to a different argument, and they can't be reused!
  val doubler4: Int => Int = _ * 2
  val adder3: (Int, Int) => Int = _ + _

  /* Exercise 1
   * Rewrite the `superAdder` function from `WhatsAFunction` using lambdas.
   */

  val superAdder: Int => Int => Int = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val superAdder2 = (x: Int) => (y: Int) => x + y
  val addTwo = superAdder(2) // `(y: Int) => 2 + y`
  val anAddition = addTwo(40) // 42
  val anAddition2 = superAdder(2)(40) // 42

  def main(args: Array[String]): Unit = {
    println(justDoSomething) // Representation of a function instance
    println(justDoSomething()) // 45
  }
}
