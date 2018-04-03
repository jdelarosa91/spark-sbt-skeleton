package com.organization.skeleton.wordcount

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}


class WordCountTest extends FlatSpec with Matchers with SharedSparkContext {

  "Wordcount" should "count how many words are in a file" in {

    val text = sc.parallelize(Seq(
      "hello bye hello",
      "bye hello"
    ))

    val count = WordCount.count(text).collectAsMap()

    count.get("bye").get shouldBe 2
    count.get("hello").get shouldBe 3

  }
}
