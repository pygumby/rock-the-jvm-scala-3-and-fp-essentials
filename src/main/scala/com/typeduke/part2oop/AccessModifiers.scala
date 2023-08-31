package com.typeduke.part2oop

object AccessModifiers {
  class Person(val name: String) {
    // `protected` = access inside a class and its children
    protected def sayHi(): String = s"Hi, my name is ${this.name}."

    // `private` = access only inside a class
    private def watchNetflix(): String = "I'm binge-watching my favorite show..."
  }

  class Kid(override val name: String, age: Int) extends Person(name) {
    def greetPolitely(): String = // No modifier = `public`
      s"${this.sayHi()} I love to play."
  }

  val aPerson = new Person("Alice")
  val aKid = new Kid("David", 5)

  // Complication in Scala
  class KidWithParents(override val name: String, age: Int, momName: String, dadName: String)
      extends Person(name) {
    val mom = new Person(this.momName)
    val dad = new Person(this.dadName)

    // Problem: Inside `KidWithParents`, `sayHi()` can only be called via `this`.
    // def everyoneSayHi(): String =
    //   s"${this.sayHi()} And here are my parents: ${mom.sayHi()} ${dad.sayHi()}"
  }

  def main(args: Array[String]): Unit = {
    // println(aPerson.sayHi()) // No longer works as `sayHi()` is now `protected`.
    println(aKid.greetPolitely())
  }
}
