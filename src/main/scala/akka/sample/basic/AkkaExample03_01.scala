package akka.sample.basic

import akka.actor.{Props, ActorSystem}
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import spray.json._
import akka.sample.actor.WeatherApi

object AkkaExample03_01 extends App {
  val system = ActorSystem()
  val actor = system.actorOf(Props[WeatherApi])
  implicit val timeout = Timeout(10.seconds)

  try {
    (1 to 5).foreach { i =>
      println(s"start $i")
      val f = (actor ? i).map {
        case (i, jsonStr: String) => jsonStr.parseJson.asJsObject
      }
      f.onSuccess {
        case (json: JsObject) =>
          println(s"$i ${json.getFields("location").head.asJsObject.getFields("city").head}")
      }
    }
    Thread.sleep(5000)
  } finally {
    system.shutdown
  }

}