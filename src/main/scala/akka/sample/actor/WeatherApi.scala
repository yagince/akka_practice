package akka.sample.actor

import akka.actor.Actor
import scala.io.Source

class WeatherApi extends Actor {
  def receive = {
    case i: Int =>
      println(s"receive $i")
      Thread.sleep(3000)
      sender ! (i, Source.fromURL("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010", "utf8").getLines.mkString)
  }
}