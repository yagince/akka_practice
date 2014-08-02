package akka.sample.basic

import akka.actor.{ActorSystem, Actor, Props}
import akka.routing.RoundRobinRouter
import akka.sample.actor.{Doubled, DoubleActor}

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2013/02/13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
object AkkaExample02 extends App {
  val system = ActorSystem("sample")
  val actor = system.actorOf(Props[Master])

  (0 to 10).foreach(actor ! _)

  Thread.sleep(100)
  system.shutdown
}

class Master extends Actor {
  val router = context.actorOf(Props[DoubleActor].withRouter(RoundRobinRouter(2)))

  def receive = {
    case i:Int => router ! i
    case Doubled(x) => println("received : %d".format(x))
  }
}
