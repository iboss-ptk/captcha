package com.thoughtworks.captcha

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by supanat on 20/12/2016.
  */
class MathExpressionTest extends FunSuite with Matchers {
  test("evaluate 4 + 5 should be 9") {
    val expression = new MathExpression('+', 4, 5)
    expression.evaluate shouldBe 9
  }

  test("evaluate 5 - 3 should be 2") {
    val expression = new MathExpression('-', 5, 3)
    expression.evaluate shouldBe 2
  }

  test("evaluate 2 * 3 should be 6") {
    val expression = new MathExpression('*', 2, 3)
    expression.evaluate shouldBe 6
  }

  test("expression asString should be 5 + 3") {
    val expression = new MathExpression('+', 5, 3)
    expression.asString shouldBe "5 + 3"
  }

  test("expression asString should be 7 - 2") {
    val expression = new MathExpression('-', 7, 2)
    expression.asString shouldBe "7 - 2"
  }
}
