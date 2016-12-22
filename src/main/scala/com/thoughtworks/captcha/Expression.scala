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

case class AlgebraicExpression(operator: Char, left: Expression, right: Expression) extends Expression {
  val operationMap = Map(
    '+' -> ((left: Int, right: Int) => left + right),
    '-' -> ((left: Int, right: Int) => left - right),
    '*' -> ((left: Int, right: Int) => left * right)
  )

  override def evaluate: Int = operationMap(operator)(left.evaluate, right.evaluate)
  override def toText = {
    def nestedString(expression: Expression): String = expression match {
      case expr: AlgebraicExpression => s"(${expr.toText})"
      case expr => expr.toText
    }
    s"${nestedString(left)} $operator ${nestedString(right)}"
  }
}

