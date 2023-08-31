package com.typeduke.part2oop

object Inheritance {
  class Animal {
    val creatureType = "wild"

    def eat(): Unit = println("Nom, nom, nom...")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      this.eat()
      println("Crunch, crunch, crunch...")
    }
  }

  val cat = new Cat

  class Person(val name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  // It's mandatory to specfiy the super constructor!
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // Overriding
  class Dog extends Animal {
    override val creatureType = "domestic"

    override def eat(): Unit = println("Mmm, I like this bone!")

    // Popular method to override
    override def toString(): String = "A dog"
  }

  // Subtype polymorphism
  val dog: Animal = new Dog()

  // Overloading vs. overriding
  class Crocodile extends Animal {
    override val creatureType: String = "very wild"

    override def eat(): Unit = println("I can eat anything, I'm a croc!")

    // Overloading: creating methods with the same name, different signatures
    def eat(animal: Animal): Unit = println("I'm eating this poor fella!")

    /* Exercise 1
     * Which method overloads are legal?
     *
     * def eat(dog: Dog): Unit = println("Eating a dog...")
     * def eat(person: Person): Unit = println(s"I'm eating a human with the name ${person.name}.")
     * def eat(person: Person, dog: Dog): Unit = println("I'm eating a human AND a dog.")
     * def eat(): Int = 45
     * def eat(dog: Dog, person: Person): Unit = println("I'm eating a human AND a dog.")
     *
     * All except the fourth variant, as it's signature doesn't differ from the super method.
     */
  }

  def main(args: Array[String]): Unit = {
    cat.eat()
    cat.crunch()

    dog.eat() // At runtime, the most specific method will be called (w.r.t subtype polymorphism).
    println(dog)
  }
}
