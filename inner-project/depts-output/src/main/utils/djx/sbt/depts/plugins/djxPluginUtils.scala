package djx.sbt.depts.plugins

import cats._
import cats.implicits._

object pUtils extends pUtils

trait pUtils {

  val initializeInstanceMonad: Monad[sbt.Def.Initialize] = new StackSafeMonad[sbt.Def.Initialize] {
    override def flatMap[A, B](fa: sbt.Def.Initialize[A])(f: A => sbt.Def.Initialize[B]): sbt.Def.Initialize[B] =
      sbt.std.InitializeInstance.flatten(sbt.std.InitializeInstance.map(fa, f))
    override def pure[A](x: A): sbt.Def.Initialize[A] = sbt.std.InitializeInstance.pure(() => x)
  }

  val sourcePosition: sbt.internal.util.SourcePosition.type = sbt.internal.util.SourcePosition

  class task(implicit m: Monad[sbt.Def.Initialize]) {
    def appendItemToSeq[T](s: sbt.TaskKey[Seq[T]])(value: () => T)(lp: sbt.SourcePosition): sbt.Def.Setting[sbt.Task[Seq[T]]] =
      appendItemToSeqImpl(s)(sbt.std.FullInstance.pure(value))(lp)

    def appendItemToSeqImpl[T](s: sbt.TaskKey[Seq[T]])(value: sbt.Def.Initialize[sbt.Task[T]])(
      lp: sbt.SourcePosition
    ): sbt.Def.Setting[sbt.Task[Seq[T]]] = s.append1(value, lp)
  }

  object task extends task()(initializeInstanceMonad)

  class setting(implicit m: Monad[sbt.Def.Initialize]) {
    def setConst[T](sKey: sbt.SettingKey[T])(value: T)(lp: sbt.SourcePosition): sbt.Def.Setting[T] =
      setKey(sKey)(value.pure[sbt.Def.Initialize])(lp)

    def setKey[T](sKey: sbt.SettingKey[T])(target: sbt.Def.Initialize[T])(lp: sbt.SourcePosition): sbt.Def.Setting[T] = sKey.set(target, lp)
    def addItemToSettingKey[T](sKey: sbt.SettingKey[Seq[T]])(key: sbt.Def.Initialize[T])(lp: sbt.SourcePosition): sbt.Def.Setting[Seq[T]] =
      sKey.append1(key, lp)

    def addScalaJsLibraryImpl(sKey: sbt.SettingKey[Seq[sbt.ModuleID]])(
      bindKey: sbt.Def.Initialize[sbt.CrossVersion]
    )(moduleOrg: String, moduleName: String, version: String)(lp: sbt.SourcePosition): sbt.Def.Setting[Seq[sbt.ModuleID]] = {
      import org.portablescala.sbtplatformdeps.{PlatformDepsPlugin, PlatformDepsGroupID}
      import PlatformDepsPlugin.autoImport._

      def moduleIDCompat[F[_]: Monad](keySetting: F[sbt.CrossVersion]): F[sbt.ModuleID] = for (crossVersionInfo <- keySetting) yield {
        val modulePrefix = PlatformDepsGroupID.withCross(moduleOrg, moduleName, crossVersionInfo)
        modulePrefix % version
      }

      addItemToSettingKey(sKey)(moduleIDCompat(bindKey))(lp)
    }

    def addScalaJsLibrary(
      sKey: sbt.SettingKey[Seq[sbt.ModuleID]]
    )(moduleOrg: String, moduleName: String, version: String)(lp: sbt.SourcePosition): sbt.Def.Setting[Seq[sbt.ModuleID]] = {
      val bindKey = org.portablescala.sbtplatformdeps.PlatformDepsGroupID.platformDepsCrossVersion
      addScalaJsLibraryImpl(sKey)(bindKey)(moduleOrg, moduleName, version)(lp)
    }

    def simpleScalaLibrary(moduleOrg: String, moduleName: String, version: String): sbt.ModuleID =
      sbt.stringToOrganization(moduleName) %% moduleName % version

    def simpleJavaLibrary(moduleOrg: String, moduleName: String, version: String): sbt.ModuleID =
      sbt.stringToOrganization(moduleName) % moduleName % version

    def simpleScalaJsLibrary(moduleOrg: String, moduleName: String, version: String)(
      bindKey: sbt.Def.Initialize[sbt.CrossVersion]
    ): sbt.Def.Initialize[sbt.ModuleID] = {
      import org.portablescala.sbtplatformdeps.{PlatformDepsPlugin, PlatformDepsGroupID}
      import PlatformDepsPlugin.autoImport._

      def moduleIDCompat[F[_]: Monad](keySetting: F[sbt.CrossVersion]): F[sbt.ModuleID] = for (crossVersionInfo <- keySetting) yield {
        val modulePrefix = PlatformDepsGroupID.withCross(moduleOrg, moduleName, crossVersionInfo)
        modulePrefix % version
      }

      moduleIDCompat(bindKey)
    }

    def scalaLibrary(moduleOrg: String, moduleName: String, version: String): sbt.Def.Initialize[sbt.ModuleID] =
      simpleScalaLibrary(moduleOrg, moduleName, version).pure[sbt.Def.Initialize]

    def javaLibrary(moduleOrg: String, moduleName: String, version: String): sbt.Def.Initialize[sbt.ModuleID] =
      simpleJavaLibrary(moduleOrg, moduleName, version).pure[sbt.Def.Initialize]

    def scalaJsLibrary(moduleOrg: String, moduleName: String, version: String): sbt.Def.Initialize[sbt.ModuleID] = {
      val bindKey = org.portablescala.sbtplatformdeps.PlatformDepsGroupID.platformDepsCrossVersion
      simpleScalaJsLibrary(moduleOrg, moduleName, version)(bindKey)
    }

  }

  object setting extends setting()(initializeInstanceMonad)

  import scala.language.experimental.macros

  object SeeTree {
    def see[U](p: U): U = macro impl.SeeTreeImpl.see[U]
  }

}

package impl {
  import scala.reflect.macros.blackbox.Context

  object SeeTreeImpl {
    def see[U: c.WeakTypeTag](c: Context)(p: c.Expr[U]): c.Expr[U] = {
      println(p.tree)
      p
    }
  }
}
