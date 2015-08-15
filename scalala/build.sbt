name := "scalala-sample"

version := "1.0.0-SNAPSHOT"

//scalaVersion := "2.11.6"
scalaVersion := "2.9.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  Seq(
    "org.scalala"                 %   "scalala_2.9.1"   % "1.0.0.RC2",
    //"org.slf4j"                   %   "slf4j-log4j12"   % "1.7.7",
    //"com.typesafe.scala-logging"  %%  "scala-logging"   % "3.1.0",
    //"org.scalatest"               %   "scalatest_2.11"  % "2.2.4"   % "test"
    "org.scalatest"               %   "scalatest_2.9.0" % "1.9.2"   % "test"
  )
}

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Scala Tools Snapshots" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo"
)

//Revolver.settings:Seq[sbt.Setting[_]]
//
// Check code style
lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value

(compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle

lazy val testScalastyle = taskKey[Unit]("testScalastyle")

testScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Test).toTask("").value

(test in Test) <<= (test in Test) dependsOn testScalastyle
