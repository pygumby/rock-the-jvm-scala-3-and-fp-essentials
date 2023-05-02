val scala3Version = "3.0.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "rock-the-jvm-scala-3-and-fp-essentials",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
