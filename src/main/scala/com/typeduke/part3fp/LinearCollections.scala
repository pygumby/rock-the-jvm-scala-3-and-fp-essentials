package com.typeduke.part3fp

import scala.util.Random

object LinearCollections {
  // `Seq`
  def testSeqs(): Unit = {
    // Seqs provide well-defined ordering and indexing.
    val aSeq = Seq(4, 2, 3, 1)

    // Main API: indexing an element
    val thirdElement = aSeq.apply(2)

    // `map`, `flatMap`, `filter`, `for`
    val anIncrementedSeq = aSeq.map(_ + 1) // [5, 3, 4, 2]
    val aFlatMappedSeq = aSeq.flatMap(x => Seq(x, x + 1)) // [4, 5, 2, 3, 3, 4, 1, 2]
    val aFilteredSequence = aSeq.filter(_ % 2 == 0) // [4, 2]

    // Other methods
    val reversed = aSeq.reverse
    val concatenation = aSeq ++ Seq(5, 6, 7)
    val sortedSeq = aSeq.sorted
    val sum = aSeq.foldLeft(0)(_ + _)
    val stringRep = aSeq.mkString("[", ",", "]")
  }

  // `List`
  def testLists(): Unit = {
    val aList = List(1, 2, 3)

    // Same API as `Seq`...
    // ...plus features of a linked list
    val first = aList.head
    val rest = aList.tail

    // Appending and prepending
    val aBiggerList = 0 +: aList :+ 4
    val prepending = 0 :: aList // `::` equivalent to `Cons` in our `LList`

    // Utility methods
    val scalaX5 = List.fill(5)("Scala")
  }

  // `Range`
  def testRanges(): Unit = {
    val range = 1 to 10
    val aNonInclusiveRange = 1 until 10 // 10 not included

    // Again, same API as `Seq`
    (1 to 10).foreach(_ => println("Scala"))
  }

  // `Array`
  def testArrays(): Unit = {
    val anArray = Array(1, 2, 3, 4, 5, 6) // `int[]` on the JVM

    // `Array` supports most `Seq` APIs...
    // ...but it does not a `Seq`!
    // Yet, it can be converted to `Seq`.
    val aSeq: Seq[Int] = anArray.toIndexedSeq

    // Arrays are MUTABLE!
    anArray.update(2, 30) // No new array is allocated.
  }

  // `Vector`
  def testVectors(): Unit = {
    // Vectors are seqs, suitable for large amounts of data.
    // They conform to the `Seq` API and don't add anything on top -- but they're fast.
    val aVector: Vector[Int] = Vector(1, 2, 3, 4, 5, 6)
  }

  // `Set`
  def testSets(): Unit = {
    // Sets disallow duplicates.
    val aSet = Set(1, 2, 3, 4, 5, 4) // 4 won't be added twice!

    // Sets are usually implemented in terms of `equals` and `hashCode`.
    // A popular implementation of `Set` is `HashSet`.

    // Main API: test whether an element is in the set
    val containsThree = aSet.contains(3) // true
    val containsThree2 = aSet(3) // equivalent to the above

    // Adding and removing
    val aBiggerSet = aSet + 4 // [1, 2, 3, 4, 5]
    val aSmallerSet = aSet - 4 // [1, 2, 3, 5]

    // Concatenation
    val anotherSet = Set(4, 5, 6, 7, 8)
    val muchBiggerSet = aSet.union(anotherSet) // [1, 2, 3, 4, 5, 6, 7, 8]
    val muchBiggerSet2 = aSet | anotherSet // `|` is an alias for `union`
    val muchBiggerSet3 = aSet ++ anotherSet // also equivalent to the above

    // Difference
    val aDiffSet = aSet.diff(anotherSet) // [1, 2, 3]
    val aDiffSet2 = aSet -- anotherSet // equivalent to the above

    // Intersection
    val anIntersection = aSet.intersect(anotherSet) // [4, 5]
    val anIntersection2 = aSet & anotherSet // equivalent to the above
  }

  // Let's naively compare the performance of `List` and `Vector`.
  def smallBenchmark(): Unit = {
    val maxRuns = 1000
    val maxCapacity = 1000000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()
        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)
        System.nanoTime() - currentTime
      }
      times.sum * 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
  }

  def main(args: Array[String]): Unit = {
    // testSeqs()
    // testLists()
    // testRanges()
    // testArrays()
    // testSets()

    smallBenchmark()
  }
}
