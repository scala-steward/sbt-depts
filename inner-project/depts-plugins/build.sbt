import djx.sbt.depts.plugins.pUtils.{setting, sourcePosition, SeeTree}

setting.setKey(scalaVersion)(sourcePosition.fromEnclosing).value(scalaV.v212)
setting.setKey(scalafmtOnCompile)(sourcePosition.fromEnclosing).value(true)

libraryDependencies ++= libScalax.`sbt-librarymanagement-core`.value
libraryDependencies ++= libScalax.`scala-collection-compat`.value
libraryDependencies ++= libScalax.`scala-sbt`.value

Compile / unmanagedSourceDirectories := {
  val v1          = (Compile / unmanagedSourceDirectories).value
  val codegenFile = sourceDirectory.value / "main" / "codegen"
  codegenFile +: v1
}
