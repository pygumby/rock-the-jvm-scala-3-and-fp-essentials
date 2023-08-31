package com.typeduke.part1basics

object StringOps {
  val aString: String = "Hello, I am learning Scala."

  // String methods
  val secondChar = aString.charAt(1) // "e"
  val firstWord = aString.substring(0, 5) // "Hello"
  val words = aString.split(" ") // Array("Hello,", "I", "am", "learning", "Scala.")
  val startsWithHello = aString.startsWith("Hello") // true
  val allDashes = aString.replace(' ', '-') // "Hello,-I-am-learning-Scala."
  val allUppercase = aString.toUpperCase() // "HELLO, I AM LEARNING SCALA."
  val nChars = aString.length // 27

  // Non-standard string methods
  val reversed = aString.reverse // ".alacS gninrael ma I ,olleH"
  val aBunchOfCharacters = aString.take(10) // "Hello, I a"

  // Parse to numeric
  val numberAsString = "2"
  val number = numberAsString.toInt

  // Interpolation
  val name = "typeduke"
  val age = 31
  val greeting = "Hello, I'm " + name + " and I am " + age + "years old."
  val greetingV2 = s"Hello, I'm $name and I am $age years old."
  val greetingV3 = s"Hello, I'm $name and I will be turning ${age + 1} years old."

  // Format interpolation
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.5f burgers per minute."

  // Raw interpolation
  val escapes = raw"This is a \n newline."

  def main(args: Array[String]): Unit = {
    println(greeting)
    println(greetingV2)
    println(greetingV3)

    println(myth)

    println(escapes)
  }
}
