package akka.sample.basic

import akka.actor.{Props, ActorSystem}
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.ExecutionContext
import akka.routing.{RoundRobinRouter, RoundRobinPool}
import spray.json._
import ExecutionContext.Implicits.global
import akka.sample.actor.WeatherApi

object AkkaExample03_02 extends App {
  val system = ActorSystem()
  val router = system.actorOf(Props[WeatherApi].withRouter(new RoundRobinPool(3)))
  implicit val timeout = Timeout(10.seconds)

  try {
    (1 to 5).foreach { i =>
      println(s"start $i")
      val f = (router ? i).map {
        case (i, jsonStr:String) => jsonStr.parseJson.asJsObject
      }
      f.onSuccess {
        case json: JsObject => println(s"$i ${json.getFields("location").head.asJsObject.getFields("city").head}")
      }
    }
    Thread.sleep(5000)
  } finally {
    system.shutdown
  }
}

