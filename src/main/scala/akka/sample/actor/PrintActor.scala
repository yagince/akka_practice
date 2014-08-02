package akka.sample.actor

import akka.actor.Actor

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2013/02/13
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
class PrintActor extends Actor {
  def receive = {
    case x:Int => {
      println(x)
      self ! x.toString
    }
    case x:String => {
      println("String: %s".format(x))
    }
  }
}