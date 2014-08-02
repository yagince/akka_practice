package akka.sample.basic

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import akka.actor.{Props, ActorSystem}
import akka.sample.actor.WeatherApi
import akka.pattern.ask
import akka.util.Timeout
import akka.routing.FromConfig

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2014/07/20
 * Time: 23:37
 * To change this template use File | Settings | File Templates.
 */
object AkkaExample04 extends App {
  val system = ActorSystem()
  val actor = system.actorOf(Props[WeatherApi].withRouter(FromConfig), "routerTest")
  implicit val timeout = Timeout(10.seconds)

  val f1 = (actor ? 1)
  val f2 = (actor ? 2)
  val f3 = (actor ? 3)

  for {
    first <- f1
    second <- f2
    third <- f3
  } yield {
    println("yeild!!")
    (first, second, third) match {
      case ((i, json1), (j, json2), (k, json3)) => print("complete")
    }
    system.shutdown
  }
  println("ここはどこ")
}
