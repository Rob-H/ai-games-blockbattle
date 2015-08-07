val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
lazy val root = (project in file(".")).
    settings(
        name := "Rob Haley's block battle bot",
        scalaVersion := "2.11.4",
        libraryDependencies += scalaTest
    )
