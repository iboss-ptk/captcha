package com.thoughtworks.captcha

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by supanat on 22/12/2016.
  */
class IntegerExpressionTest extends FunSuite with Matchers {
  test("evaluate should returns its own raw value") {
    IntegerExpression(3).evaluate shouldBe 3
    IntegerExpression(5).evaluate shouldBe 5
  }

  test("toText should returns string of its own raw value") {
    IntegerExpression(3).toText shouldBe "3"
    IntegerExpression(5).toText shouldBe "5"
  }
}
