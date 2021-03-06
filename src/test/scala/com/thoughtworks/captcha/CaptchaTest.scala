package com.thoughtworks.captcha

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by supanat on 20/12/2016.
  */
class CaptchaTest extends FunSuite with Matchers with MockFactory {

  private val stubExpression = stub[Expression]
  private val stubGenerator = stub[Generator]

  test("check 0 should return true when evaluated expression is 0") {
    stubExpression.evaluate _ when() returns 0
    val captcha = Captcha(stubExpression)
    captcha.check(0) shouldBe true
  }

  test("check 1 should return false when evaluated expression is 0") {
    stubExpression.evaluate _ when() returns 0
    val captcha = Captcha(stubExpression)
    captcha.check(1) shouldBe false
  }

  test("$createWith should contain expression generated by generator") {
    stubGenerator.next _ when() returns stubExpression
    Captcha.createWith(stubGenerator).expression shouldBe stubExpression
  }
}
