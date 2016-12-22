package com.thoughtworks.captcha

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by supanat on 21/12/2016.
  */
class AlgebraicExpressionGeneratorTest extends FunSuite with Matchers {
  test("next should return  1 * 2 when randomized operand are 1, 2 and operation is *") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(false, false)
      override def operandGenList: List[Int] = List(1, 2)
      override def operatorGenList: List[Char] = List('*')
    }.next

    expr shouldBe AlgebraicExpression('*', IntegerExpression(1), IntegerExpression(2))
  }

  test("next should return  4 - 2 when randomized operand are 4, 2 and operation is -") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(false, false)
      override def operandGenList: List[Int] = List(4, 2)
      override def operatorGenList: List[Char] = List('-')
    }.next

    expr shouldBe AlgebraicExpression('-', IntegerExpression(4), IntegerExpression(2))
  }

  test("next should return  4 - (2 + 3) when randomized operand are 4, 2, 3 and operation is -, +") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(false, true, false, false)
      override def operandGenList: List[Int] = List(4, 2, 3)
      override def operatorGenList: List[Char] = List('-', '+')
    }.next

    expr shouldBe AlgebraicExpression(
      '-',
      IntegerExpression(4),
      AlgebraicExpression('+', IntegerExpression(2), IntegerExpression(3)))
  }

  test("next should return (4 * 5) - (2 + 3) when randomized operand are 4, 2, 3 and operation is -, +") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(true, false, false, true, false, false)
      override def operandGenList: List[Int] = List(4, 5, 2, 3)
      override def operatorGenList: List[Char] = List('-','*', '+')
    }.next

    expr shouldBe AlgebraicExpression(
      '-',
      AlgebraicExpression('*', IntegerExpression(4), IntegerExpression(5)),
      AlgebraicExpression('+', IntegerExpression(2), IntegerExpression(3)))
  }

  test("next should return (4 * 5) - (2 + 3) when randomized operand are 4, 2, 3 and operation is -, + " +
    "but not exceeding depth 1 by default") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(true, true, false, true, false, false)
      override def operandGenList: List[Int] = List(4, 5, 2, 3)
      override def operatorGenList: List[Char] = List('-','*', '+')
    }.next

    expr shouldBe AlgebraicExpression(
      '-',
      AlgebraicExpression('*', IntegerExpression(4), IntegerExpression(5)),
      AlgebraicExpression('+', IntegerExpression(2), IntegerExpression(3)))
  }

  trait FakeRandomizer extends Randomizer {
    override val nextIsBranching: () => Boolean = statefulNext(isBranchingGenList)
    override val nextOperand: () => Int = statefulNext(operandGenList)
    override val nextOperator: () => Char = statefulNext(operatorGenList)

    def isBranchingGenList: List[Boolean]
    def operandGenList: List[Int]
    def operatorGenList: List[Char]

    private def statefulNext[T](genList: List[T]) = {
      var count = 0
      () => {
        val oldCount = count
        count = count + 1
        genList(oldCount)
      }
    }
  }
}
