package com.typeduke.part1basics

object CbvVsCbn {
  // CBV = call by value = arguments are evaluated before function invocation.
  def aFunction(arg: Int): Int = arg + 1
  val aComputation = aFunction(23 + 67)

  // CBN = call by name = arguments are passed LITERALLY, evaluated at every reference.
  def aByNameFunction(arg: => Int): Int = arg + 1
  val anotherComputation = aByNameFunction(23 + 67)

  def printTwiceByValue(x: Long): Unit = {
    println(s"By value: $x")
    println(s"By value: $x")
  }

  // Argument evaluation is delayed.
  // Argument is evaluated every time it is used.
  def printTwiceByName(x: => Long): Unit = {
    println(s"By name: $x")
    println(s"By name: $x")
  }

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  def main(args: Array[String]): Unit = {
    println(aComputation)
    println(anotherComputation)

    printTwiceByValue(System.nanoTime())
    printTwiceByName(System.nanoTime())

    printFirst(42, infinite())
  }
}
