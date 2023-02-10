package MyConfig

import scopt._

case class Config(
                     hostname: String="",

                     port:Int=5432,
                     database:String="",
                     username:String="",
                     password:String="",
                     table:String="" ,
                     parquet:String="",
                     csv:String=""

                   )
object Config {
  val parser: OptionParser[Config] = new OptionParser[Config]("prognameEx38") {
    head("Ex38")

    opt[String]("hostname") required() action {
      (v, c) => c.copy(hostname = v)
    }

    opt[Int]("port") required() action {
      (v, c) => c.copy(port = v)
    }

    opt[String]("database") required() action {
      (v, c) => c.copy(database = v)
    }

    opt[String]("username") required() action {
      (v, c) => c.copy(username = v)
    }

    opt[String]("password") required() action {
      (v, c) => c.copy(password = v)
    }

    opt[String]("table") required() action {
      (v, c) => c.copy(table = v)
    }

    opt[String]("parquet") required() action {
      (v, c) => c.copy(parquet = v)
    }

    opt[String]("csv") required() action {
      (v, c) => c.copy(csv = v)
    }


    override def errorOnUnknownArgument = false

    override def showUsageOnError = true
  }

  def parseArgs(args: Array[String]): Config = {
    parser.parse(args, Config()) match {
      case Some(conf) => conf
      case None => sys.error("Could not parse arguments")
    }
  }}
