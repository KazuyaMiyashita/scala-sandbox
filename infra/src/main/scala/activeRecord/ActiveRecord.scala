package infra.activeRecord

import java.sql.{Array => _, _}

object Main extends App {

  Class.forName("com.mysql.cj.jdbc.Driver")
  val conn: Connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/scala-sandbox", "root", null)
  val stmt: Statement  = conn.createStatement()
  val rset: ResultSet  = stmt.executeQuery("show columns from users;")
  def getStrings(rset: ResultSet): List[String] = {
    def proc(acc: List[String]): List[String] = {
      if (rset.next()) proc(rset.getString(1) :: acc)
      else acc
    }
    proc(Nil)
  }
  val columns: List[String] = getStrings(rset)

  // trait Record {
  //   def get(column: String): String
  // }
  // def getRecords(table: String): List[Record] = {
  //   val stmt: Statement  = conn.createStatement()
  //   val rset: ResultSet  = stmt.executeQuery("select * from ${table};")
  //   val metaData: ResultSetMetaData = rset.getMetaData()

  // }

  println(columns)
  rset.close()
  stmt.close()
  conn.close()
}
