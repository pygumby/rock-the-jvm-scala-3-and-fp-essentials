package com.typeduke.part2oop

object AbstractDataTypes {
  // Abstract class
  abstract class Animal {
    val creatureType: String // Abstract field

    def eat(): Unit // Abstract method
    // Methods without arguments and parentheses are "accessor methods".
    def preferredMeal: String = "anything" // Non-abstract method
  }

  // Abstract classes can't be instantiated.
  // val animal = new Animal

  // Non-abstract classes must implement the abstract fields and methods.
  class Dog extends Animal {
    override val creatureType: String = "domestic"
    // Overriding also works for non-abstract methods and fields.
    // Scala allows for non-abstract accessor methods to be overridden AS FIELDS.
    override val preferredMeal: String = "bones"

    override def eat(): Unit = println("Crunching this bone...")
  }

  val aDog: Animal = new Dog()

  // Traits
  trait Carnivore { // Since Scala 3, traits can also have constructor arguments.
    def eat(animal: Animal): Unit // Traits can also have non-abstract fields and methods.
  }

  class TRex extends Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals!")
  }

  // Practical difference between traits and abstract classes:
  // Single inheritance with classes vs. multiple inheritance with traits

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType = "croc"

    override def eat(): Unit = println("I'm a croc, I just crunch stuff.")
    override def eat(animal: Animal) = println("I'm eating an animal.")
  }

  // Philosophical difference between traits and abstract classes:
  // - Abstract classes are THINGS.
  // - Traits are BEHAVIORS.

  // Scala type hierarchy
  // Any
  // |_ AnyRef - all classes we write
  //    |_ scala.Null - the null reference
  // |_ AnyVal - the primitive types
  //    |_ Int, Boolean, Char,
  // |_ scala.Nothing

  val aNonExistentAnimal: Animal = null
  // val anInt: Int = throw new NullPointerException // Only way to "instantiate" the type `Nothing`

  def main(args: Array[String]): Unit = {}
}
