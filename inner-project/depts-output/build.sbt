scalaVersion := scalaV.v212

addSbtPlugin("org.portable-scala" % "sbt-platform-deps" % "1.0.1")

scalafmtOnCompile := true

enablePlugins(SbtPlugin)

pluginCrossBuild / sbtVersion := {
  scalaBinaryVersion.value match {
    case "2.12" => "1.8.2" // set minimum sbt version
  }
}

organization := "net.scalax"
name         := "sbt-depts-djx314"
version      := "0.0.1-M5"

Compile / unmanagedSourceDirectories := {
  val v1          = (Compile / unmanagedSourceDirectories).value
  val codegenFile = sourceDirectory.value / "main" / "codegen"
  codegenFile +: v1
}
