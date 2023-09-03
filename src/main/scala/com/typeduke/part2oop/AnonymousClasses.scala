package com.typeduke.part2oop

object AnonymousClasses {
  abstract class Animal {
    def eat(): Unit
  }

  // Classes used for just one instance are boilerplate-y.
  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I'm a weird animal!")
  }

  val someAnimal = new SomeAnimal

  // The above is equivalent to the following anonymous class instantiation.
  val someAnimal2 = new Animal {
    override def eat(): Unit = println("I'm a weird animal!")
  }

  // What happens behind the scenes:
  // class AnonymousClasses.AnonClass$1 extends Animal {
  //   override def eat(): Unit = println("I'm a weird animal!")
  // }
  //
  // val someAnimal2 = new AnonymousClasses.AnonClass$1

  // This is available for abstract classes and traits, but also for non-abstract classes.
  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi, my name is $name.")
  }

  val somePerson = new Person("Jim") {
    override def sayHi(): Unit = println("Hi, my name is JIM!")
  }

  def main(args: Array[String]): Unit = {}
}
