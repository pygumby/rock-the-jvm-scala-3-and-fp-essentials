package com.typeduke.part4power

import com.typeduke.practice.*

object AllThePatterns {
  object MySingleton

  // 1 - Constants
  val someValue: Any = "Scala"
  val constants = someValue match {
    case 42          => "A number"
    case "Scala"     => "A string"
    case true        => "The truth"
    case MySingleton => "My singleton object"
  }

  // 2 - `match` anything
  val matchAnythingVar = 2 + 3 match {
    case something => s"I've matched anything, it's $something."
  }

  val matchAnything = someValue match {
    case _ => "I can match anything at all!"
  }

  // 3 - Tuples
  val aTuple = (1, 4)
  val matchTuple = aTuple match {
    case (1, somethingElse) => s"A tuple with 1 and $somethingElse"
    case (something, 2)     => "A tuple with 2 as its second field"
  }

  // Pattern matching structures can be nested
  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => "A nested tuple"
  }

  // 4 - Case classes
  val aList: LList[Int] = Cons(1, Cons(2, Empty()))
  val matchList = aList match {
    case Empty()                   => "An empty list"
    case Cons(head, Cons(_, tail)) => s"A non-empty list starting with $head"
  }

  // 5 - Options
  val anOption: Option[Int] = Option(2)
  val matchOption = anOption match {
    case None        => "An empty option"
    case Some(value) => s"A non-empty option containing $value"
  }

  // 6 - List patterns
  val aStandardList = List(1, 2, 3, 42)
  val matchStandardList = aStandardList match {
    case List(1, _, _, _)    => "List with four elements, first is 1"
    case List(1, _*)         => "List starting with 1"
    case List(1, 2, _) :+ 42 => "List ending in 42"
    case head :: tail        => "Deconstructed list"
    case Nil                 => "Empty list" // Added to avoid compiler warning
  }

  // 7 - Type specifiers
  val unknown: Any = 2
  val machtedType = unknown match {
    case anInt: Int      => s"I matched an integer and added 2 to it: ${anInt + 2}"
    case aString: String => "I matched a string."
    case _: Double       => "I matched a double I don't care about."
  }

  // 8 - Name binding
  val bindingNames = aList match {
    case Cons(head, rest @ Cons(_, tail)) => s"I can reference the 'rest' name: $rest"
  }

  // 9 - Chained patterns
  val multiMatch = aList match {
    case Empty() | Cons(0, _) => "An empty list to me"
    case _                    => "Anything else"
  }

  // 10 - `if` guards
  val secondElemSpecial = aList match {
    case Cons(_, Cons(specialElem, _)) if specialElem > 5 => "The second element is big enough."
    case _ => "Anything else"
  }

  /* Exercise 1
   * Does the below code make sense?
   */

  val aSimpleInt = 45

  // This is an anti-pattern:
  val isEvenBad = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _               => false
  }

  // Similarily bad:
  val isEvenBad2 = if (aSimpleInt % 2 == 0) true else false

  // Good:
  val isEven = aSimpleInt % 2 == 0

  /* Exercise 2
   * Which string will be returned?
   */

  val numbers: List[Int] = List(1, 2, 3, 4)

  // It'll be the first case, as type arguments are removed at runtime.
  // Throws compiler warning, hence commented out
  // val numbersMatch = numbers match {
  //   case listOfStrings: List[String] => "A list of strings"
  //   case listOfInts: List[Int]       => "A list of ints"
  // }

  def main(args: Array[String]): Unit = {
    // println(numbersMatch)
  }
}
