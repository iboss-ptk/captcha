package com.thoughtworks.captcha

import org.scalatest.{FunSuite, Matchers}

import scala.util.Random

/**
  * Created by supanat on 21/12/2016.
  */
class AlgebraicExpressionGeneratorTest extends FunSuite with Matchers {
  test("next should return  1 * 2 when randomized operand are 1, 2 and operation is *") {
    val stubRandom = createStubRandom(
      operandList = List(1, 2),
      operationList = List(2),
      depth = 1)

    val expr = new AlgebraicExpressionGenerator(stubRandom).next

    expr shouldBe AlgebraicExpression('*', IntegerExpression(1), IntegerExpression(2))
  }

  test("next should return  4 - 2 when randomized operand are 4, 2 and operation is -") {
    val stubRandom = createStubRandom(
      operandList = List(4, 2),
      operationList = List(1),
      depth = 1)

    val expr = new AlgebraicExpressionGenerator(stubRandom).next

    expr.left shouldBe IntegerExpression(4)
    expr.right shouldBe IntegerExpression(2)
    expr.operation shouldBe '-'
  }

  ignore("next should return  4 - (2 + 3) when randomized operand are 4, 2, 3 and operation is -, +") {
    val stubRandom = createStubRandom(
      operandList = List(4, 2),
      operationList = List(1, 0),
      depth = 2)

    val expr = new AlgebraicExpressionGenerator(stubRandom).next

    expr.left shouldBe IntegerExpression(4)
    expr.right shouldBe AlgebraicExpression(
      '+',
      IntegerExpression(2),
      IntegerExpression(3))
    expr.operation shouldBe '-'
  }

  private def createStubRandom(operandList: List[Int], operationList: List[Int], depth: Int) = new Random {
    val operandBound = 10
    val operationBound = 3
    val depthBound = 2

    var operandCount = 0
    var operationCount = 0

    override def nextInt(num: Int) =
      if(num == operandBound) {
        val oldOperandCount = operandCount
        operandCount = operandCount + 1
        operandList(oldOperandCount)
      }
      else if(num == operationBound) {
        val oldOperationCount = operationCount
        operationCount = operationCount + 1
        operationList(oldOperationCount)
      }
      else if(num == depthBound) {
        depth
      }
      else
        0
  }
}
