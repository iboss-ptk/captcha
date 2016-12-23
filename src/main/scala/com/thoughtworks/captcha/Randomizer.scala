package com.thoughtworks.captcha

import scala.util.Random

/**
  * Created by supanat on 22/12/2016.
  */
trait Randomizer {
  def nextIsBranching: () => Boolean = () => Random.nextBoolean
  def nextOperand: () => Int = () => Random.nextInt(10)
  def nextOperator: () => Operator = () => Operator.all(Random.nextInt(Operator.count))
}
