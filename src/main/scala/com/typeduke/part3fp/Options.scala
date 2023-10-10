package com.typeduke.part3fp

import scala.util.Random

object Options {
  // Options == "collections" with at most one value
  val anOption: Option[Int] = Option(42)
  val anEmptyOption: Option[Int] = Option.empty

  // Alternative, "discouraged"
  val anOption2: Option[Int] = Some(42)
  val anEmptyOption2: Option[Int] = None

  // "Standard" API
  val isEmpty = anOption.isEmpty
  val innerValue = anOption.getOrElse(90)
  val anotherOption = Option(46)
  val aChainedOption = anEmptyOption.orElse(anotherOption)

  // `map`, `flatMap`, `filter`, `for`
  val anIncrementedOption = anOption.map(_ + 1) // `Some(43)`
  val aFilteredOption = anIncrementedOption.filter(_ % 2 == 0) // `None`
  val aFlatMappedOption = anOption.flatMap(value => Option(value + 10)) // `Some(420)`

  // Purpose of options: to work with unsafe APIs
  def unsafeMethod(): String = null
  def fallbackMethod(): String = "Some valid result"

  // "Defensive style" of dealing with unsafe APIs: `null` checks
  val stringLength = if (unsafeMethod() == null) -1 else unsafeMethod().length

  // "Option-style": no `null` checks
  // Passing `null` into the `apply` method creates an empty `Option`.
  val stringLengthOption = Option(unsafeMethod()).map(_.length())

  // Use case for `orElse`
  val chain = Option(unsafeMethod()).orElse(Option(fallbackMethod()))

  // Recommended design
  def betterUnsafeMethod(): Option[String] = None
  def betterFallbackMethod(): Option[String] = Some("A valid result")
  def betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  // Example: `Map.get`
  val phoneBook = Map(
    "Daniel" -> 1234
  )
  // No need to crash or to check for `null`s or whether Mary is present:
  val marysPhoneNumber = phoneBook.get("Mary") // `None`

  /* Exercise 1
   * Connect safely
   *
   * 1. Get the host and port from the configuration.
   * 2. Try to open a connection.
   * 3. Print "Connection successful"...
   * 4. ...or "Connection failed".
   */

  val config: Map[String, String] = Map(
    // Comes from elsewhere, cannot be changed by us.
    "host" -> "176.45.32.1",
    "port" -> "8081"
  )

  class Connection {
    def connect(): String = "Connection successful"
  }

  object Connection {
    val random = new Random()

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // "Defensive-style" solution
  // val host = config("host")
  // val port = config("port")
  // if (host != null)
  //   if (port != null)
  //     val conn = Connection(host, port).get
  //     if (conn != null)
  //       conn.connect()

  // Option-style solution
  val host = config.get("host")
  val port = config.get("port")
  val conn: Option[Connection] = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connStatus: Option[String] = conn.map(_.connect())

  // Option-style solution, compacted
  val connStatus2 = config
    .get("host")
    .flatMap(h => config.get("port").flatMap(p => Connection(h, p).map(_.connect())))

  // Option-style solution, `for` comprehension
  val connStatus3 = for {
    h <- config.get("host")
    p <- config.get("port")
    conn <- Connection(h, p)
  } yield conn.connect()

  def main(args: Array[String]): Unit = {
    // Exercise 1
    println(connStatus.getOrElse("Connection failed"))
    println(connStatus2.getOrElse("Connection failed"))
    println(connStatus3.getOrElse("Connection failed"))
  }
}
