package com.thoughtworks.captcha

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by supanat on 21/12/2016.
  */
class AlgebraicExpressionGeneratorTest extends FunSuite with Matchers with ExpressionDSL {
  
  test("next should return  1 * 2 when randomized operand are 1, 2 and operation is *") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(false, false)
      override def operandGenList: List[Int] = List(1, 2)
      override def operatorGenList: List[Operator] = List(<*>)
    }.next

    expr shouldBe e$(<*>, n(1), n(2))
  }

  test("next should return  4 - 2 when randomized operand are 4, 2 and operation is -") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(false, false)
      override def operandGenList: List[Int] = List(4, 2)
      override def operatorGenList: List[Operator] = List(<->)
    }.next

    expr shouldBe e$(<->, n(4), n(2))
  }

  test("next should return  4 - (2 + 3) when randomized operand are 4, 2, 3 and operation is -, +") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(false, true, false, false)
      override def operandGenList: List[Int] = List(4, 2, 3)
      override def operatorGenList: List[Operator] = List(<->, <+>)
    }.next

    expr shouldBe e$(
      <->,
      n(4),
      e$(<+>, n(2), n(3)))
  }

  test("next should return (4 * 5) - (2 + 3) when randomized operand are 4, 2, 3 and operation is -, +") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(true, false, false, true, false, false)
      override def operandGenList: List[Int] = List(4, 5, 2, 3)
      override def operatorGenList: List[Operator] = List(<->,<*>, <+>)
    }.next

    expr shouldBe e$(
      <->,
      e$(<*>, n(4), n(5)),
      e$(<+>, n(2), n(3)))
  }

  test("next should return (4 * 5) - (2 + 3) when randomized operand are 4, 2, 3 and operation is -, + " +
    "but not exceeding depth 1 by default") {
    val expr = new AlgebraicExpressionGenerator() with FakeRandomizer {
      override def isBranchingGenList: List[Boolean] = List(true, true, false, true, false, false)
      override def operandGenList: List[Int] = List(4, 5, 2, 3)
      override def operatorGenList: List[Operator] = List(<->,<*>, <+>)
    }.next

    expr shouldBe e$(
      <->,
      e$(<*>, n(4), n(5)),
      e$(<+>, n(2), n(3)))
  }

  trait FakeRandomizer extends Randomizer {
    override val nextIsBranching: () => Boolean = statefulNext(isBranchingGenList)
    override val nextOperand: () => Int = statefulNext(operandGenList)
    override val nextOperator: () => Operator = statefulNext(operatorGenList)

    def isBranchingGenList: List[Boolean]
    def operandGenList: List[Int]
    def operatorGenList: List[Operator]

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
