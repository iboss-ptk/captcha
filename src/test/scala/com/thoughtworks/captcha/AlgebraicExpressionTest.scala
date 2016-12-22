package com.thoughtworks.captcha

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by supanat on 20/12/2016.
  */
class AlgebraicExpressionTest extends FunSuite with Matchers {
  test("evaluate 4 + 5 should be 9") {
    val expression = AlgebraicExpression('+', IntegerExpression(4), IntegerExpression(5))
    expression.evaluate shouldBe 9
  }

  test("evaluate 5 - 3 should be 2") {
    val expression = AlgebraicExpression('-', IntegerExpression(5), IntegerExpression(3))
    expression.evaluate shouldBe 2
  }

  test("evaluate 2 * 3 should be 6") {
    val expression = AlgebraicExpression('*', IntegerExpression(2), IntegerExpression(3))
    expression.evaluate shouldBe 6
  }

  test("evaluate 2 - (3 * 4) should be -10") {
    val expression = AlgebraicExpression(
      '-',
      IntegerExpression(2),
      AlgebraicExpression(
        '*', IntegerExpression(3), IntegerExpression(4)))

    expression.evaluate shouldBe -10
  }

  test("evaluate (3 * 4) - 2 should be 10") {
    val expression = AlgebraicExpression(
      '-',
      AlgebraicExpression(
        '*', IntegerExpression(3), IntegerExpression(4)),
      IntegerExpression(2))

    expression.evaluate shouldBe 10
  }

  test("evaluate (3 * 4) + (2 + 1) should be 15") {
    val expression = AlgebraicExpression(
      '+',
      AlgebraicExpression(
        '*', IntegerExpression(3), IntegerExpression(4)),
      AlgebraicExpression(
        '+', IntegerExpression(2), IntegerExpression(1)))

    expression.evaluate shouldBe 15
  }

  test("expression asString should be 5 + 3") {
    val expression = AlgebraicExpression('+', IntegerExpression(5), IntegerExpression(3))
    expression.asString shouldBe "5 + 3"
  }

  test("expression asString should be 7 - 2") {
    val expression = AlgebraicExpression('-', IntegerExpression(7), IntegerExpression(2))
    expression.asString shouldBe "7 - 2"
  }

  test("expression asString should be 2 - (3 * 4)") {
    val expression = AlgebraicExpression(
      '-',
      IntegerExpression(2),
      AlgebraicExpression(
        '*', IntegerExpression(3), IntegerExpression(4)))

    expression.asString shouldBe "2 - (3 * 4)"
  }

  test("expression asString should be (3 * 4) - 2") {
    val expression = AlgebraicExpression(
      '-',
      AlgebraicExpression(
        '*', IntegerExpression(3), IntegerExpression(4)),
      IntegerExpression(2))

    expression.asString shouldBe "(3 * 4) - 2"
  }

  test("expression asString should be (3 * 4) + (2 + 1)") {
    val expression = AlgebraicExpression(
      '+',
      AlgebraicExpression(
        '*', IntegerExpression(3), IntegerExpression(4)),
      AlgebraicExpression(
        '+', IntegerExpression(2), IntegerExpression(1)))

    expression.asString shouldBe "(3 * 4) + (2 + 1)"
  }
}
