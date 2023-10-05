package com.typeduke.practice

import scala.annotation.tailrec

object TuplesMapsExercises {
  /* Exercise 1
   * Implement a basic social network.
   *
   * The social network is represented by a `Map[String, Set[String]]`, mapping members to friends.
   * All friendships are mutual.
   *
   * Core features:
   * - Add a person to the network
   * - Remove a person from the network
   * - Add a friend relationship
   * - Remove a friend relationship
   *
   * "On-top" features:
   * - How many friends has a person?
   * - Who has the most friends?
   * - How many people have no friends?
   * - Is there a social connection between two people (directly or transitively)?
   */

  def addPerson(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def removePerson(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    (network - person).map(pair => (pair._1, pair._2 - person))

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    if (!network.contains(a)) throw new IllegalArgumentException(s"Person $a doesn't exist.")
    else if (!network.contains(b)) throw new IllegalArgumentException(s"Person $b doesn't exist.")
    else {
      val friendsOfA = network(a)
      val friendsOfB = network(b)

      network + (a -> (friendsOfA + b)) + (b -> (friendsOfB + a))
    }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    if (!network.contains(a) || !network.contains(b)) network
    else {
      val friendsOfA = network(a)
      val friendsOfB = network(b)

      network + (a -> (friendsOfA - b), b -> (friendsOfB - a))
    }

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) -1
    else network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String =
    if (network.isEmpty) throw new RuntimeException("Network is empty.")
    else {
      val best = network.foldLeft(("", -1)) { (current, next) =>
        val currentNFriends = current._2
        val nextNFriends = next._2.size

        if (currentNFriends < nextNFriends) (next._1, nextNFriends)
        else current
      }
      best._1
    }

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    // Breadth-first search
    @tailrec
    def search(discoveredPeople: Set[String], consideredPeople: Set[String]): Boolean =
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        val personsFriends = network(person)

        if (personsFriends.contains(b)) true
        else
          search(
            discoveredPeople - person ++ personsFriends -- consideredPeople,
            consideredPeople + person
          )
      }

    if (!network.contains(a) || !network.contains(b)) false
    else search(network(a), Set(a))
  }

  def main(args: Array[String]): Unit = {
    // Core features
    val empty: Map[String, Set[String]] = Map()
    val network = addPerson(addPerson(empty, "Bob"), "Mary")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))

    // "On-top" features
    val people = addPerson(addPerson(addPerson(empty, "Bob"), "Mary"), "Jim")
    val simpleNetwork = friend(friend(people, "Bob", "Mary"), "Jim", "Mary")
    println(simpleNetwork)
    println(nFriends(simpleNetwork, "Mary")) // 2
    println(nFriends(simpleNetwork, "Bob")) // 1
    println(nFriends(simpleNetwork, "Daniel")) // -1
    println(mostFriends(simpleNetwork)) // "Mary"
    println(nPeopleWithNoFriends(addPerson(simpleNetwork, "Daniel"))) // 1
    println(socialConnection(simpleNetwork, "Bob", "Jim")) // true
    println(socialConnection(addPerson(simpleNetwork, "Daniel"), "Bob", "Daniel")) // false
  }
}
