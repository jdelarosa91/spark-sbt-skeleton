package com.organization.skeleton

import com.organization.skeleton.wordcount.WordCount

import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]): Unit = {

    val testSeq = Seq("asda asas da", "sada as dasd ada da", "asd as das as da asd")


    val runLocal = args.length > 0 && args(args.length-1).equals("L")
    var spark: SparkSession = null

    if (runLocal) {
      spark = SparkSession
        .builder()
        .appName("WordCount")
        .master("local[*]")
        .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .config("spark.broadcast.compress", "false")
        .config("spark.shuffle.compress", "false")
        .config("spark.shuffle.spill.compress", "false")
        .enableHiveSupport()
        .getOrCreate()
    } else {
      spark = SparkSession
        .builder()
        .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .appName("WordCount")
        .enableHiveSupport()
        .getOrCreate()
    }

    val fileRDD = spark.sparkContext.parallelize(testSeq)
    val count = WordCount.count(fileRDD)

    // scalastyle:off println
    count.collect.foreach(println)
    // scalastyle:on println

    spark.close()

  }

}
