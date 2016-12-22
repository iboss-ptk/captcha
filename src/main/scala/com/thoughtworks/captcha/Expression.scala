package com.thoughtworks.captcha

/**
  * Created by supanat on 20/12/2016.
  */
trait Expression {
  def evaluate: Int
  def asString: String
}

class MathExpression(val operation: Char, val left: Int, val right: Int) extends Expression {
  val operationMap = Map(
    '+' -> ((left: Int, right: Int) => left + right),
    '-' -> ((left: Int, right: Int) => left - right),
    '*' -> ((left: Int, right: Int) => left * right)
  )

  override def evaluate: Int = operationMap(operation)(left, right)
  override def asString = s"$left $operation $right"
}

