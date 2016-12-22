import com.thoughtworks.captcha.{Captcha, MathExpressionGenerator}

/**
  * Created by supanat on 21/12/2016.
  */
object Main extends App {
  val captcha = Captcha.createWith(new MathExpressionGenerator())
  println(captcha.expression.asString)
  val answer = scala.io.StdIn.readLine("= ").toInt

  println(s"you are ${ if(captcha.check(answer)) "not " else ""}robot!")
}
