VarContext.setScalaVersion := "2.11"
scalaVersion               := "2.11.12"

VarContext.changeDeptVar         := "kind-projector"
addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)

VarContext.changeDeptVar       := "better-monadic-for"
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")

VarContext.changeDeptVar           := "circe"
libraryDependencies += "io.circe" %%% "circe-core"    % "0.11.2"
libraryDependencies += "io.circe" %%% "circe-generic" % "0.11.2"
libraryDependencies += "io.circe" %%% "circe-parser"  % "0.11.2"

VarContext.changeDeptVar                := "scalatest"
libraryDependencies += "org.scalactic" %%% "scalactic"          % "3.2.17"
libraryDependencies += "org.scalatest" %%% "scalatest"          % "3.2.17"
libraryDependencies += "org.scalatest" %%% "scalatest-core"     % "3.2.17"
libraryDependencies += "org.scalatest" %%% "scalatest-funsuite" % "3.2.17"

VarContext.changeDeptVar                := "cats-effect"
libraryDependencies += "org.typelevel" %%% "cats-effect" % "2.0.0"

VarContext.changeDeptVar                := "cats"
libraryDependencies += "org.typelevel" %%% "cats-core" % "2.0.0"
libraryDependencies += "org.typelevel" %%% "cats-free" % "2.0.0"

VarContext.changeDeptVar               := "h2"
libraryDependencies += "com.h2database" % "h2" % "2.2.224"

VarContext.changeDeptVar              := "doobie"
libraryDependencies += "org.tpolecat" %% "doobie-core"      % "0.7.1"
libraryDependencies += "org.tpolecat" %% "doobie-hikari"    % "0.7.1"
libraryDependencies += "org.tpolecat" %% "doobie-postgres"  % "0.7.1"
libraryDependencies += "org.tpolecat" %% "doobie-scalatest" % "0.7.1"

VarContext.changeDeptVar              := "doobie-h2"
libraryDependencies += "org.tpolecat" %% "doobie-h2" % "0.7.1"

VarContext.changeDeptVar          := "zio2"
libraryDependencies += "dev.zio" %%% "zio"          % "2.0.10"
libraryDependencies += "dev.zio" %%% "zio-streams"  % "2.0.10"
libraryDependencies += "dev.zio" %%% "zio-test"     % "2.0.10"
libraryDependencies += "dev.zio" %%% "zio-test-sbt" % "2.0.10"

VarContext.changeDeptVar             := "typesafe-config"
libraryDependencies += "com.typesafe" % "config" % "1.4.2"

VarContext.changeDeptVar                         := "scala-collection-compat"
libraryDependencies += "org.scala-lang.modules" %%% "scala-collection-compat" % "2.11.0"

VarContext.changeDeptVar             := "http4s-Release"
libraryDependencies += "org.http4s" %%% "http4s-dsl"   % "0.20.23"
libraryDependencies += "org.http4s" %%% "http4s-circe" % "0.20.23"

VarContext.changeDeptVar            := "http4s-twirl"
libraryDependencies += "org.http4s" %% "http4s-twirl" % "0.20.23"

VarContext.changeDeptVar          := "zio-config"
libraryDependencies += "dev.zio" %%% "zio-config"            % "3.0.7"
libraryDependencies += "dev.zio" %%% "zio-config-typesafe"   % "3.0.7"
libraryDependencies += "dev.zio" %%% "zio-config-yaml"       % "3.0.7"
libraryDependencies += "dev.zio" %%% "zio-config-derivation" % "3.0.7"

VarContext.changeDeptVar          := "slf4j-simple"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.9"

VarContext.changeDeptVar                   := "commons-io"
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"

VarContext.changeDeptVar                           := "macwire"
libraryDependencies += "com.softwaremill.macwire" %%% "macros" % "2.3.7"

VarContext.changeDeptVar                   := "commons-lang3"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.13.0"

VarContext.changeDeptVar            := "commons-lang3-bridge"
libraryDependencies += "net.scalax" %% "commons-lang3-bridge" % "0.1.0"

VarContext.changeDeptVar                    := "simple-adt"
libraryDependencies += "net.scalax.simple" %%% "simple-adt" % "0.0.2-M6"

VarContext.changeDeptVar               := "sbt-librarymanagement-core"
libraryDependencies += "org.scala-sbt" %% "librarymanagement-core" % "1.2.4"

VarContext.changeDeptVar             := "javacv-platform"
libraryDependencies += "org.bytedeco" % "javacv-platform" % "1.5.9"

VarContext.changeDeptVar              := "shapeless"
libraryDependencies += "com.chuusai" %%% "shapeless" % "2.3.10"

VarContext.changeDeptVar         := "fs2"
libraryDependencies += "co.fs2" %%% "fs2-core"             % "2.1.0"
libraryDependencies += "co.fs2" %%% "fs2-io"               % "2.1.0"
libraryDependencies += "co.fs2"  %% "fs2-reactive-streams" % "2.1.0"

VarContext.changeDeptVar                    := "slick"
libraryDependencies += "com.typesafe.slick" %% "slick"          % "3.3.3"
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen"  % "3.3.3"

VarContext.changeDeptVar               := "logback-classic"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.11"

VarContext.changeDeptVar                  := "javet"
libraryDependencies += "com.caoccao.javet" % "javet" % "2.2.3"

VarContext.changeDeptVar              := "jintellitype"
libraryDependencies += "com.melloware" % "jintellitype" % "1.4.1"

VarContext.changeDeptVar                 := "jnativehook"
libraryDependencies += "com.github.kwhat" % "jnativehook" % "2.2.2"

VarContext.changeDeptVar         := "zio-interop-reactivestreams"
libraryDependencies += "dev.zio" %% "zio-interop-reactivestreams" % "2.0.2"

VarContext.changeDeptVar         := "zio-logging"
libraryDependencies += "dev.zio" %% "zio-logging" % "0.5.16"

VarContext.changeDeptVar          := "mysql-connector-java"
libraryDependencies += "com.mysql" % "mysql-connector-j" % "8.1.0"

VarContext.changeDeptVar               := "postgresql-jdbc"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.27"

VarContext.changeDeptVar                        := "scala-java8-compat"
libraryDependencies += "org.scala-lang.modules" %% "scala-java8-compat" % "0.9.0"
