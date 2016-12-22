import com.thoughtworks.captcha.{AlgebraicExpressionGenerator, Captcha}

import scala.util.Try

/**
  * Created by supanat on 21/12/2016.
  */
object Main extends App {
  val captcha = Captcha.createWith(new AlgebraicExpressionGenerator())
  println(captcha.expression.toText)

  val isVerified = Try {
    val answer = scala.io.StdIn.readLine("= ").toInt
    captcha.check(answer)
  }.getOrElse(false)

  println(s"you are ${ if(isVerified) "verified" else "robot" }!")
}
