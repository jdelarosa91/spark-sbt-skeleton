package com.organization.skeleton.wordcount

import org.apache.spark.rdd.RDD

object WordCount {

  /**
    * Method to count every word in a rdd
    * @param file rdd of strings with words separated by spaces.
    */
  def count(file: RDD[String]): RDD[(String, Int)] = {
    file.flatMap(_.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
  }
}
