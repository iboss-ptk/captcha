package com.thoughtworks.captcha

import scala.util.Random


/**
  * Created by supanat on 21/12/2016.
  */
trait Generator {
  def next: Expression
}

class AlgebraicExpressionGenerator(random: Random = scala.util.Random, depthBound: Int = 2) extends Generator {
  override def next: AlgebraicExpression = {
      val operations = List('+', '-', '*')

      def nextDown(depthBound: Int): AlgebraicExpression = {
        val randomOperation = operations(random.nextInt(operations.length))

        AlgebraicExpression(
          operation = randomOperation,
          left = IntegerExpression(random.nextInt(10)),
          right = IntegerExpression(random.nextInt(10)))
      }

    nextDown(depthBound)
  }
}