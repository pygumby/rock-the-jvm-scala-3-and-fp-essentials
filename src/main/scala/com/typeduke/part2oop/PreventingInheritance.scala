package com.typeduke.part2oop

object PreventingInheritance {

  class Person(name: String) {
    // The method `enjoylife()` cannot be overridden if the `final` keyword is present.
    final def enjoyLife(): Int = 42
  }

  class Adult(name: String) extends Person(name) {
    // Overriding a `final` method results in a compile-time error.
    // override def enjoyLife(): Int = 999
  }

  // The class `Animal` cannot be extended if the `final` keyword is present.
  final class Animal

  // Extinding a `final` class results in a compile-time error.
  // class Cat extends Animal

  // Sealing a class will prevent its further inheritance outside of the file of its definition.
  sealed class Guitar(nStrings: Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // Heavy use of inheritance is quite unproductive and, hence, discouraged in Scala.
  // Scala 3 has a new keyword to signal extensibility explicitly as it's otherwise not encouraged.
  // This is not a mandatory requirement but a good practice.

  open class ExtensibleGuitar(nStrings: Int)

  def main(args: Array[String]): Unit = {}
}
