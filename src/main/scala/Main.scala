import org.apache.spark.sql.{SaveMode, SparkSession, functions}

import java.util.Properties
import MyConfig.Config
import org.apache.spark.sql.functions.{col, count, mean, round}

object Main {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .appName("MySuperCode")
      .getOrCreate()

    val conf = Config.parseArgs(args)
    println(conf.toString)

    val driver = "org.postgresql.Driver"
    //Class.forName(driver)
    val jdbcHostname = conf.hostname
    val jdbcPort = conf.port
    val jdbcDatabase = conf.database
    val jdbcUrl = s"jdbc:postgresql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}"
    val jdbcUsername = conf.username
    val jdbcPassword = conf.password
    val table = conf.table

    val connectionProperties = new Properties()
    connectionProperties.setProperty("user", s"${jdbcUsername}")
    connectionProperties.setProperty("password", s"${jdbcPassword}")
    connectionProperties.setProperty("Driver", driver)

    val df1 = spark.read.parquet(conf.parquet)
    df1.cache()
    val df2 = spark.read.option("header", value = true).option("inferSchema", value = true).csv(conf.csv)
    df2.cache()
    import spark.implicits._
    val df1_dataset = df1.where(col("trip_distance")>0).as[TaxiRow]
    val df2_dataset = df2.as[ZoneRow]
    val result_dataset = df1_dataset.as("t")
      .join(df2_dataset.as("z"),col("t.DOLocationID")===col("z.LocationID"))
      .groupBy(col("Borough"))
      .agg(
        count("*").as("total_trip"),
        round(functions.max("trip_distance"),2).as("max_trip"),
        round(functions.min("trip_distance"),2).as("min_trip"),
        round(mean("trip_distance"),2).as("mean_trip"))
    result_dataset.show()
    result_dataset.write.mode(SaveMode.Overwrite).jdbc(jdbcUrl, table, connectionProperties)
    val test_table = spark.read.jdbc(jdbcUrl, table, connectionProperties)
    test_table.show()
  }
}