package com.typeduke.part1basics

object ValuesAndTypes {
  // Values
  val meaningOfLife: Int = 42

  // Assigning is not allowed.
  // meaningOfLife = 45

  // Type inference
  val anInteger = 67 // `: Int` is optional.

  // Common types (mapped to JVM primitive types)
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = 48 // 4 bytes
  val aShort: Short = 5263 // 2 bytes
  val aLong: Long = 52789572389234L // 8 bytes
  val aFloat: Float = 2.4f // 4 bytes
  val aDouble: Double = 3.14 // 8 bytes

  // String
  val aString: String = "Scala"

  def main(args: Array[String]): Unit = {}
}
