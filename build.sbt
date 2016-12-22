name := "captcha"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= List(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.4.2" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

mainClass in (Compile, run) := Some("Main")