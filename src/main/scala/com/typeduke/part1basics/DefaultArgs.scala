package com.typeduke.part1basics

import scala.annotation.tailrec

object DefaultArgs {
  def sumUntilTailRec(x: Int, acc: Int = 0): Int =
    if (x <= 0) acc
    else sumUntilTailRec(x - 1, acc + x)

  val sumUntil100 = sumUntilTailRec(100) // The additional argument is passed automatically.

  // When you use a function mostly with the same arguments, use default arguments.
  def savePicture(
      path: String,
      name: String,
      format: String = "jpg",
      width: Int = 1920,
      height: Int = 1080
  ): Unit =
    println(s"Saving picture in format $format and path $path.")

  def main(args: Array[String]): Unit = {
    // Default args are injected.
    savePicture("users/typeduke/photos", "my-photo")
    // Explicit values are passed for default args.
    savePicture("users/typeduke/photos", "my-photo", "png")
    // Values are passed after the default arg.
    savePicture("users/typeduke/photos", "my-photo", width = 800, height = 600)
    // Naming arguments allows passing them in a different order.
    savePicture("users/typeduke/photos", "my-photo", height = 600, width = 800)
  }
}
