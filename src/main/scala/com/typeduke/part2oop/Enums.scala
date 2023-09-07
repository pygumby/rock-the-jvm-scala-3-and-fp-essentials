package com.typeduke.part2oop

object Enums {
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // As enums are a data type, you can add fields and methods.
    def openDocument(): Unit =
      if (this == READ) println("Opening document...")
      else println("Opening now allowed...")
  }

  val somePermissions: Permissions = Permissions.READ

  // Enums can take constructor arguments.
  enum PermissionsWithBits(val bits: Int) { // `val` was omitted, but then I couldn't access it.
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // And enums can have companion objects.
  object PermissionsWithBits {
    // Daniel's comment to the implementation is "whatever".
    // The point is that factory methods go here.
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  // Enum's standard API
  val somePermissionsOrdinal = somePermissions.ordinal // Index at which it was defined
  val allPermissions = PermissionsWithBits.values // Array of all possible values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ") // `Permissions.READ`

  def main(args: Array[String]): Unit = {
    // `Permissons`
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
    println(readPermission)

    // `PermissionsWithBits`
    println(PermissionsWithBits.READ.bits)
  }
}
