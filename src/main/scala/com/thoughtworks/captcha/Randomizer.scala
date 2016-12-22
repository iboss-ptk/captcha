package com.thoughtworks.captcha

import scala.util.Random

/**
  * Created by supanat on 22/12/2016.
  */
trait Randomizer {
  def nextIsBranching = () => Random.nextBoolean
  def nextOperand = () => Random.nextInt(10)
  def nextOperator = () => List('+', '-', '*')(Random.nextInt(2))
}
