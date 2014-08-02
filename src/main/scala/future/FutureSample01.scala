package future

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2014/07/20
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
object FutureSample01 extends App {
  object Numbers {
    def even(i: Int) = {
      if (i % 2 == 0) i
      else throw new IllegalArgumentException(s"${i} is not even.")
    }
    def equal(i: Int, j: Int) = {
      if (i == j) i
      else throw new IllegalArgumentException(s"${i} is not ${j}.")
    }
  }

  val f = Future {
    Numbers.even(2)
  }.map { i =>
    Numbers.equal(i, 2)
  }
  f.onSuccess {
    case i => println("Success")
  }
  f.onFailure {
    case e => println(e.getMessage)
  }
  f.onComplete {
    case _ => println("complete")
  }
}
