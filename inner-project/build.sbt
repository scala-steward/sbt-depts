import Settings._

scalaVersion := "2.13.10"

lazy val `depts-abs`     = project in rootFile / "depts-abs"
lazy val `depts-codegen` = (project in rootFile / "depts-codegen").dependsOn(`depts-abs`)
lazy val `depts-output`  = project in outputFile

genAction := {
  (`depts-abs` / genActionImpl).inputTaskValue.evaluated
  (`depts-codegen` / genActionImpl).inputTaskValue.evaluated
}

addCommandAlias("push1", "; reload; depts-output/publishLocal")

ThisBuild / djxScalafmtFile := rootFile / ".djx314-scalafmt-common.conf"
ThisBuild / djxBuildSbtFile := rootFile / "project" / "build.properties"
