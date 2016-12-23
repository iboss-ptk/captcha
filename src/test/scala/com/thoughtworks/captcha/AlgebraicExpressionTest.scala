package com.thoughtworks.captcha

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by supanat on 20/12/2016.
  */
class AlgebraicExpressionTest extends FunSuite with Matchers with ExpressionDSL {
  
  test("evaluate 4 + 5 should be 9") {
    val expression = e$(<+>, n(4), n(5))
    expression.evaluate shouldBe 9
  }

  test("evaluate 5 - 3 should be 2") {
    val expression = e$(<->, n(5), n(3))
    expression.evaluate shouldBe 2
  }

  test("evaluate 2 * 3 should be 6") {
    val expression = e$(<*>, n(2), n(3))
    expression.evaluate shouldBe 6
  }

  test("evaluate 2 - (3 * 4) should be -10") {
    val expression = e$(<->, n(2), e$(<*>, n(3), n(4)))

    expression.evaluate shouldBe -10
  }

  test("evaluate (3 * 4) - 2 should be 10") {
    val expression = e$(
      <->,
      e$(<*>, n(3), n(4)),
      n(2))

    expression.evaluate shouldBe 10
  }

  test("evaluate (3 * 4) + (2 + 1) should be 15") {
    val expression = e$(
      <+>,
      e$(<*>, n(3), n(4)),
      e$(<+>, n(2), n(1)))

    expression.evaluate shouldBe 15
  }

  test("expression asString should be 5 + 3") {
    val expression = e$(<+>, n(5), n(3))
    expression.toText shouldBe "5 + 3"
  }

  test("expression asString should be 7 - 2") {
    val expression = e$(<->, n(7), n(2))
    expression.toText shouldBe "7 - 2"
  }

  test("expression asString should be 2 - (3 * 4)") {
    val expression = e$(
      <->,
      n(2),
      e$(<*>, n(3), n(4)))

    expression.toText shouldBe "2 - (3 * 4)"
  }

  test("expression asString should be (3 * 4) - 2") {
    val expression = e$(
      <->,
      e$(<*>, n(3), n(4)),
      n(2))

    expression.toText shouldBe "(3 * 4) - 2"
  }

  test("expression asString should be (3 * 4) + (2 + 1)") {
    val expression = e$(
      <+>,
      e$(<*>, n(3), n(4)),
      e$(<+>, n(2), n(1)))

    expression.toText shouldBe "(3 * 4) + (2 + 1)"
  }
}
