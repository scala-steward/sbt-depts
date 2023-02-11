package djx.sbt.depts.abs

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.util.Using
import scala.io.Source

object CodegenAction {

  def main(arr: Array[String]): Unit = {
    def genString(pathStr: String): String = Files.readString(Paths.get(pathStr))
    val strCol                             = arr.take(4).map(genString)

    val writePath = Paths.get(arr(4))
    locally {
      Files.createDirectories(writePath.getParent)
    }

    val strLines = Using.resource(Source.fromString(strCol.mkString("\n")))(l => l.getLines().to(List)).map(t => " " * 10 + t)

    Using.resource(new PrintWriter(writePath.toFile)) { printer =>
      val writeStr = s"""
         |package djx.sbt.depts
         |package codegen
         |
         |object LibraryDeptsInstance extends _root_.djx.sbt.depts.abs.LibraryDepts {
         |
         |  ${strLines.mkString("\n\n")}
         |
         |}
         |""".stripMargin

      printer.println(writeStr)
    }
  }

}