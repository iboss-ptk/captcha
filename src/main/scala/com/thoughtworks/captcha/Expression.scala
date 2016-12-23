package com.thoughtworks.captcha

/**
  * Created by supanat on 20/12/2016.
  */
trait Expression {
  def evaluate: Int
  def toText: String
}

case class IntegerExpression(value: Int) extends Expression {
  override def evaluate: Int = value
  override def toText: String = value.toString
}

case class AlgebraicExpression(operator: Operator, left: Expression, right: Expression)
  extends Expression {
  override def evaluate: Int = operator(left.evaluate, right.evaluate)
  override def toText = {
    def nestedString(expression: Expression): String = expression match {
      case expr: AlgebraicExpression => s"(${expr.toText})"
      case expr => expr.toText
    }
    s"${nestedString(left)} $operator ${nestedString(right)}"
  }
}

trait ExpressionDSL {
  def e$(operator: Operator, left: Expression, right: Expression): AlgebraicExpression =
    AlgebraicExpression(operator, left, right)

  def n(number: Int) = IntegerExpression(number)
}