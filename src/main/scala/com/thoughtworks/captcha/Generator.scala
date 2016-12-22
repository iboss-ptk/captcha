package com.thoughtworks.captcha

/**
  * Created by supanat on 21/12/2016.
  */
trait Generator {
  def next: Expression
}

class AlgebraicExpressionGenerator(depthBound: Int = 1)
  extends Generator
    with Randomizer {
  override def next: AlgebraicExpression = {
      def nextDown(depth: Int): AlgebraicExpression = {
        def recurOrTerminate =
          if(nextIsBranching() && depth > 0)
            nextDown(depth - 1)
          else
            IntegerExpression(nextOperand())

        AlgebraicExpression(
          operator = nextOperator(),
          left = recurOrTerminate,
          right = recurOrTerminate)
      }

    nextDown(depthBound)
  }
}
