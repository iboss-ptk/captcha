package com.thoughtworks.captcha

/**
  * Created by supanat on 20/12/2016.
  */
case class Captcha(expression: Expression) {
  def check(answer: Int): Boolean = answer == expression.evaluate
}

object Captcha {
  def createWith(generator: Generator): Captcha =
    new Captcha(generator.next)
}

