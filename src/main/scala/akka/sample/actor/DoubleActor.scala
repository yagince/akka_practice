package akka.sample.actor

import akka.actor.Actor
import akka.sample.message.Message

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2013/02/13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
class DoubleActor extends Actor {
  def receive = {
    case i:Int => sender ! Doubled(i*2)
  }
}

case class Doubled(i:Int) extends Message
