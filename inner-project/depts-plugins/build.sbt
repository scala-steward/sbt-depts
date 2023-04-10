import djx.sbt.depts.plugins.djxPluginUtils.{settingKeyObj => oSettingKey, sourcePosition}

oSettingKey.setConst(scalaVersion)(() => scalaV.v212)(sourcePosition.fromEnclosing)
oSettingKey.setConst(scalafmtOnCompile)(() => true)(sourcePosition.fromEnclosing)

libraryDependencies ++= libScalax.`sbt-librarymanagement-core`.value
libraryDependencies ++= libScalax.`scala-collection-compat`.value
libraryDependencies ++= libScalax.`scala-sbt`.value

Compile / unmanagedSourceDirectories := {
  val v1          = (Compile / unmanagedSourceDirectories).value
  val codegenFile = sourceDirectory.value / "main" / "codegen"
  codegenFile +: v1
}

Compile / unmanagedSourceDirectories := {
  val v1          = (Compile / unmanagedSourceDirectories).value
  val codegenFile = sourceDirectory.value / "main" / "utils"
  codegenFile +: v1
}
