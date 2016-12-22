package com.thoughtworks.captcha

/**
  * Created by supanat on 20/12/2016.
  */
trait Expression {
  def evaluate: Int
  def asString: String
}

case class IntegerExpression(value: Int) extends Expression {
  override def evaluate: Int = value
  override def asString: String = value.toString
}

case class AlgebraicExpression(operation: Char, left: Expression, right: Expression) extends Expression {
  val operationMap = Map(
    '+' -> ((left: Int, right: Int) => left + right),
    '-' -> ((left: Int, right: Int) => left - right),
    '*' -> ((left: Int, right: Int) => left * right)
  )

  override def evaluate: Int = operationMap(operation)(left.evaluate, right.evaluate)
  override def asString = {
    def nestedString(expression: Expression): String = expression match {
      case expr: AlgebraicExpression => s"(${expr.asString})"
      case expr => expr.asString
    }
    s"${nestedString(left)} $operation ${nestedString(right)}"
  }
}
