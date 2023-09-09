package com.typeduke.part2oop

// In Scala 3, names and functions can also be defined at the top level.
// DISCOURAGED!
val meaningOfLife: Int = 42
def computeMyLife: String = "Scala"

object PackagesImports {
  // Fully qualified name
  val aList: com.typeduke.practice.LList[Int] = ??? // throws `NotImplementedError`

  // `import`s can be added "pretty much everywhere".
  import com.typeduke.practice.LList
  val anotherList: LList[Int] = ???

  // `import` under an alias
  import java.util.{List as JList}
  val aJavaList: JList[Int] = ???

  // `import` everything
  import com.typeduke.practice.*
  val aPredicate: Predicate[Int] = ???

  // `import` multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // `import` everything but something
  object PlayingPhysics {
    import PhysicsConstants.{PLACK as _, *}

    // val plack = PLACK // Not available
    val c = SPEED_OF_LIGHT
  }

  import com.typeduke.part2oop.*
  val mol = meaningOfLife

  // Default imports
  // scala.*, scala.Predef.*, java.lang.*

  // Exports
  class PhysicsCalculator {
    import PhysicsConstants.*

    def computePhotonEnergy(waveLength: Double): Double =
      PLACK / waveLength
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    // Makes `computePhotonEnergy` available in the scope of `ScienceApp`
    export this.physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(waveLength: Double): Double =
      this.computePhotonEnergy(waveLength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
  }

  def main(args: Array[String]): Unit = {}
}

object PhysicsConstants {
  // Constants
  val SPEED_OF_LIGHT = 299792458
  val PLACK = 6.62e-34
  val EARTH_GRAVITY = 9.8
}
