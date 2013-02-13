package akka.sample

import actor.PrintActor
import akka.actor.{Props, ActorSystem}

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2013/02/13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
object AkkaExample01 extends App {
  val system = ActorSystem("sample")
  val actor = system.actorOf(Props[PrintActor], "hoge")

  actor ! "HelloWorld!"

  system.shutdown
}
