package com.typeduke.part3fp

object MapFlapMapFilterFor {
  // Standard list
  val aList = List(1, 2, 3) // [1] -> [2] -> [3] -> Nil
  val firstElement = aList.head
  val restOfElements = aList.tail

  // `map`
  val anIncrementedList = aList.map(_ + 1)

  // `filter`
  val onlyOddNumbers = aList.filter(_ % 2 != 0)

  // `flatMap`
  val toPair = (x: Int) => List(x, x + 1)
  val aFlatMappedList = aList.flatMap(toPair) // [1, 2, 2, 3, 3, 4]

  // All possible combinations of the below lists, in the format "1a - black"
  // Later addition: only even numbers
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  val combinations = numbers
    .withFilter(_ % 2 == 0)
    .flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))

  // `for` comprehension
  // That's exactly the same thing as the above `flatMap`/`map` chains...
  // ...and it's an expression, as it's deconstructed by the compiler into such a chain!
  val combinationsFor = for {
    number <- numbers if number % 2 == 0 // Generator with `if` guard
    char <- chars
    color <- colors
  } yield s"$number$char - $color"

  /* Exercise 1
   * Do `LList`s support `for` comprehensions?
   *
   * Yes, because `flatMap` and `map` are implemented.
   * Also, we added `withFilter` to enable `if` guards.
   */

  import com.typeduke.practice.*

  val simpleNumbers: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))

  val toPairList: Int => LList[Int] = (x: Int) => Cons(x, Cons(x + 1, Empty()))

  val incrementedNumbers = simpleNumbers.map(_ + 1)
  val filteredNumbers = simpleNumbers.filter(_ % 2 == 0)
  val flatMappedNumbers = simpleNumbers.flatMap(toPairList)

  // `map` and `flapMap` on `LList` enable `for` comprehensions.
  // `withFilter` on `LList` enables `if` guards.
  val combinationsNumbers = for {
    number <- simpleNumbers if number % 2 == 0
    char <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$number-$char"

  def main(args: Array[String]): Unit = {
    println(combinations)
    println(combinationsFor)

    // `for` comprehension with Unit (i.e., with side effects)
    numbers.foreach(println)
    for {
      number <- numbers
    } println(number)

    // Exercise 1
    println(combinationsNumbers)
  }
}
