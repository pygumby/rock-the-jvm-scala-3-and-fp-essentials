package com.typeduke.part2oop

object Objects {
  // Object/singleton != class instance
  // An object defines a type as well as the only instance of it:
  object MySingleton {
    val aField = 45

    def aMethod(x: Int) = x + 1
  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton

  val theSingletonField = MySingleton.aField
  val theSingletonMethodCall = MySingleton.aMethod(99)

  // Companions = Class and object of the same name
  // They can access each other's private fields and methods.
  class Person(name: String) {
    def sayHi(): String = s"Hi, my name is ${this.name}."
  }

  // Companion object
  object Person {
    val N_EYES = 2 // Notation for constants that do not depend on an instance

    def canFly(): Boolean = false
  }

  // Methods and fields in classes are used for instance-dependent functionality.
  val mary = new Person("Mary")
  val mary2 = new Person("Mary")
  val marysGreeting = mary.sayHi()

  // Methods and fields in objects are used for instance-independent, i.e., "static", functionality.
  val humansCanFly = Person.canFly()
  val nEyesHuman = Person.N_EYES

  // Equality
  // 1 - equality of reference (usually defined as `==`, including in Java)
  val sameMary = mary eq mary2 // false, different instances
  val sameSingleton = MySingleton eq MySingleton // true
  // 2 - equality of "sameness" (in Java defined as `equals`)
  val sameMary2 = mary equals mary2 // false, as default `equals` checks for reference equality, too
  val sameMary3 = mary == mary2 // means the same as `equals`
  val sameSingleton2 = MySingleton == MySingleton // true

  // Objects can extend classes
  object BigFoot extends Person("Big Foot")

  // Scala application = object + main method
  def main(args: Array[String]): Unit = {
    println(isSameSingleton)
  }
}
