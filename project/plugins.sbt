addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")
resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers ++= Seq(
  "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

addSbtPlugin("net.ceedubs" %% "sbt-ctags" % "0.1.0")
