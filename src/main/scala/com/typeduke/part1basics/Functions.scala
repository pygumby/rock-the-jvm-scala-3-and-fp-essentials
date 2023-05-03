package com.typeduke.part1basics

object Functions {
  // Function = Reusable piece of code that you can invoke with some arguments and return a result
  def aFunction(a: String, b: Int): String =
    a + " " + b // Function bodies are ALWAYS one expression.

  // Function invocation
  val aFunctionInvocation = aFunction("Scala", 999999999)

  def aParameterlessFunction(): Int = 42
  def anotherParameterlessFunction: Int = 42 // Idiomatic

  // Functions can be recursive.
  // When you need loops, USE recursion.
  def stringConcatenation(str: String, n: Int): String =
    if (n == 0) ""
    else if (n == 1) str
    else str + stringConcatenation(str, n - 1)

  val scalaX3 = stringConcatenation("Scala", 3)

  // "void" functions
  def aVoidFunction(aString: String): Unit = println(aString)

  // Side effects are discouraged.
  def computeDoubleStringWithSideEffect(aString: String): String = {
    aVoidFunction(aString) // Unit
    aString + aString // Meaningful value
  }

  def aBigFunction(n: Int): Int = {
    // Small, auxiliary function
    def aSmallerFunction(a: Int, b: Int): Int =
      a + b

    aSmallerFunction(n, n + 1)
  }

  // Exercises

  // 1) A greeting function (name, age) => "Hi, my name is $name and I'm $age years old."

  def greet(name: String, age: Int): String = s"Hi, my name is $name and I'm $age years old."

  assert(greet("Lucas", 31) == "Hi, my name is Lucas and I'm 31 years old.")

  // 2) Factorial function n => 1 * 2 * ... * n

  def fact(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case n => n * fact(n - 1)
  }

  assert(fact(0) == 0)
  assert(fact(5) == 120)

  // 3) Fibonacci function
  //    fib(1) = 1
  //    fib(2) = 1
  //    fib(3) = 1 + 1
  //    fib(n) = fib(n - 1) + fib(n - 2)

  def fib(n: Int): Int =
    if (n <= 2) 1
    else fib(n - 1) + fib(n - 2)

  assert(fib(42) == 267914296)
  assert(fib(5) == 5)

  // 4) A function testing whether a number is a prime number

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  assert(isPrime(7))
  assert(!isPrime(8))

  def main(args: Array[String]): Unit = {
    println(greet("Lucas", 31))
    println(fact(5))
    println(fib(5))
    println(isPrime(7))
  }
}
