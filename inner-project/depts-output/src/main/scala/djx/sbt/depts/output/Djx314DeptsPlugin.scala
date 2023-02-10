package djx.sbt.depts
package output

import sbt._
import sbt.Keys._

import impl.{ScalafmtRewrite, UpdatePluginLibVersion, UpdateSbtVersion}

package impl {
  class BuildKeysAbs {
    val djxIsScala211 = settingKey[Boolean]("Is scala 2.11")
    val djxIsScala212 = settingKey[Boolean]("Is scala 2.12")
    val djxIsScala213 = settingKey[Boolean]("Is scala 2.13")
    val djxIsScala3   = settingKey[Boolean]("Is scala 3")
  }

  class BuildKeysImpl extends BuildKeys {
    val djxScalafmtFile         = settingKey[File]("Key of scalafmt file.")
    val djxUpdateScalafmtConfig = taskKey[Unit]("update scalafmt configuration file.")

    val djxBuildSbtFile     = settingKey[File]("Key of build.properties file.")
    val djxUpdateSbtVersion = taskKey[Unit]("update sbt version configuration file.")

    val djxPluginsLigFile       = settingKey[File]("Key of plugins's build.sbt file.")
    val djxUpdatePluginsVersion = taskKey[Unit]("update plugins's build.sbt file.")

    val djxUpdate = taskKey[Unit]("All update action for this plugin.")

    import djx.sbt.depts.plugins.{PluginsCollection => DjxPluginCol}
    val djx314Plugins: DjxPluginCol = DjxPluginCol
  }
}

object Djx314DeptsPlugin extends AutoPlugin {
  override def requires: Plugins      = org.portablescala.sbtplatformdeps.PlatformDepsPlugin
  override def trigger: PluginTrigger = allRequirements

  object autoImport extends impl.BuildKeysImpl

  private class Settings(override val buildKeys: impl.BuildKeysImpl) extends Djx314DeptsImpl {
    import buildKeys._
    val updateScalafmtConfigSetting = djxUpdateScalafmtConfig := {
      val fileOpt: Option[File] = djxScalafmtFile.?.value
      for (file <- fileOpt) yield ScalafmtRewrite.writeToFile(file.toPath)
    }
    val updateSbtVersionSetting = djxUpdateSbtVersion := {
      val fileOpt: Option[File] = djxBuildSbtFile.?.value
      for (file <- fileOpt) yield UpdateSbtVersion.update(file.toPath)
    }
    val djxUpdatePluginsVersionSetting = djxUpdatePluginsVersion := {
      val fileOpt: Option[File] = djxPluginsLigFile.?.value
      for (file <- fileOpt) yield UpdatePluginLibVersion.update(file.toPath)
    }
    val djxUpdateAllSetting = djxUpdate := {
      djxUpdateScalafmtConfig.value
      djxUpdateSbtVersion.value
      djxUpdatePluginsVersion.value
    }

    val kindProjectorSetting = {
      import autoImport._
      libScalax.`kind-projector` := {
        for (lib <- libScalax.`kind-projector`.value) yield compilerPlugin(lib)
      }
    }

    val scalaVersionSettings = List(
      djxIsScala211 := { CrossVersion.partialVersion(scalaVersion.value) == Some(2L, 11L) },
      djxIsScala212 := { CrossVersion.partialVersion(scalaVersion.value) == Some(2L, 12L) },
      djxIsScala213 := { CrossVersion.partialVersion(scalaVersion.value) == Some(2L, 13L) },
      djxIsScala3   := { CrossVersion.partialVersion(scalaVersion.value).map(_._1) == Some(3L) }
    )

    override def settingsForDept: Seq[Setting[_]] =
      scalaVersionSettings ++: super.settingsForDept ++: kindProjectorSetting +: djxUpdatePluginsVersionSetting +: updateScalafmtConfigSetting +: updateSbtVersionSetting +: Seq(
        djxUpdateAllSetting
      )
  }

  private val settingsValue                                    = new Settings(autoImport)
  override def projectSettings: Seq[_root_.sbt.Def.Setting[_]] = settingsValue.settingsForDept
}
