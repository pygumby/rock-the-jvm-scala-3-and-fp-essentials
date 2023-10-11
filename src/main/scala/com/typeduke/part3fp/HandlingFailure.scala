package com.typeduke.part3fp

import scala.util.{Try, Success, Failure}
import scala.util.Random

object HandlingFailure {
  // Try = a potentially failed computation
  val aTry: Try[Int] = Try(42)
  // The following is evaluated "by name", i.e., it doesn't immediately crash the application.
  val aFailedTry: Try[Int] = Try(throw new RuntimeException)

  // Alternative, "discouraged"
  val aTry2: Try[Int] = Success(42)
  val aFailedTry2: Try[Int] = Failure(new RuntimeException)

  // Main API
  val checkSuccess = aTry.isSuccess
  val checkFailure = aTry.isFailure
  val aChain = aFailedTry.orElse(aTry)

  // `map`, `flatMap`, `filter`, `for` comprehensions
  val anIncrementedTry = aTry.map(_ + 1)
  val aFlatMappedTry = aTry.flatMap(mol => Try(s"My meaning of life is $mol."))
  val aFilteredTry = aTry.filter(_ % 2 == 0)

  // Purpose of trys: deal with unsafe APIs which may throw exceptions
  def unsafeMethod(): String = throw new RuntimeException("No string for you, buster!")

  // "Defensive style" of dealing with unsafe methods: `try`-`catch`-`finally`
  val stringLengthDefensive =
    try {
      val aString = unsafeMethod()
      aString.length
    } catch {
      case e: RuntimeException => -1
    }

  // "Purely functional": `Try`
  val stringLengthPure = Try(unsafeMethod()).map(_.length).getOrElse(-1)

  // Recommended design
  def betterUnsafeMethod(): Try[String] =
    Failure(new RuntimeException("No string for you, buster!"))
  def betterFallbackMethod(): Try[String] = Success("Scala")
  val stringLengthPure2 = betterUnsafeMethod().map(_.length).getOrElse(-1)
  val betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  /* Exercise 1
   * Connect and get page safely
   *
   * - Obtain a connection.
   * - Fetch the URL.
   * - Print the resulting HTML.
   */

  val host = "localhost"
  val port = "8081"
  val url = "typeduke.com"

  class Connection {
    val random = new Random()

    def getPage(url: String): String = {
      if (this.random.nextBoolean()) "<html>Success</html>"
      else throw new RuntimeException("Fetching page failed")
    }

    def getPageSafely(url: String): Try[String] =
      Try(this.getPage(url))
  }

  object HttpService {
    val random = new Random()

    def getConnection(host: String, port: String): Connection =
      if (this.random.nextBoolean()) new Connection
      else throw new RuntimeException("Accessing host/port combination failed")

    def getConnectionSafely(host: String, port: String): Try[Connection] =
      Try(this.getConnection(host, port))
  }

  // Defensive-style solution
  // val finalHtml = try {
  //   val conn = HttpService.getConnection(host, port)
  //   val html = try {
  //     conn.getPage(url)
  //   } catch {
  //     case e: RuntimeException => s"<html>${e.getMessage}</html>"
  //   }
  // } catch {
  //   case e: RuntimeException => s"<html>${e.getMessage}</html>"
  // }

  // Purely functional solution
  val maybeConn = Try(HttpService.getConnection(host, port))
  val maybeHtml = maybeConn.flatMap(conn => Try(conn.getPage(url)))
  val finalHtml = maybeHtml.fold(e => s"<html>${e.getMessage()}</html>", s => s)

  // Purely functional solution, `for` comprehension
  val maybeHtml2 = for {
    conn <- HttpService.getConnectionSafely(host, port)
    html <- conn.getPageSafely(url)
  } yield html
  val finalHtml2 = maybeHtml2.fold(e => s"<html>${e.getMessage()}</html>", s => s)

  def main(args: Array[String]): Unit = {
    // Exercise 1
    println(finalHtml)
  }
}
