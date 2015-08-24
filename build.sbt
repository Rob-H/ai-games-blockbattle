val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
lazy val root = (project in file(".")).
    settings(
        name := "Rob Haley's block battle bot",
        scalaVersion := "2.11.4",
        libraryDependencies += scalaTest
    )

scalastyleFailOnError := true

val scalaStyleTask = taskKey[Unit]("scalaStyle");

scalaStyleTask := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value

val scalaStyleOnTestTask = taskKey[Unit]("scalaStyleTest");

scalaStyleOnTestTask := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Test).toTask("").value

val ctags = net.ceedubs.sbtctags.CtagsKeys.genCtags

(test in Test) <<= (test in Test) dependsOn (scalaStyleOnTestTask, scalaStyleTask, ctags)
