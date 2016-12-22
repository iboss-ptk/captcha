package com.thoughtworks.captcha

import scala.util.Random


/**
  * Created by supanat on 21/12/2016.
  */
trait Generator {
  def next: Expression
}

class MathExpressionGenerator(random: Random = scala.util.Random) extends Generator {
  override def next: MathExpression = {
      val operations = List('+', '-', '*')
      val randomOperation = operations(random.nextInt(operations.length))

      new MathExpression(
        operation = randomOperation,
        left = random.nextInt(10),
        right = random.nextInt(10))
  }
}