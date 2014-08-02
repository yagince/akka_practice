package akka.sample.typed

import akka.actor.{ActorSystem, TypedActor, TypedProps}
import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.reflect.api
import scala.reflect

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2014/07/24
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
object TypedActor01 extends App {
  trait WAPI {
    def getWeather(): Future[String]
    def getWeatherNowPlease(): Option[String]
  }
  class WAPIImpl extends WAPI {
    def getWeather(): Future[String] = {
      Future.successful(Source.fromURL("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010", "utf8").getLines.mkString)
    }
    def getWeatherNowPlease(): Option[String] = {
      Some(Source.fromURL("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010", "utf8").getLines.mkString)
    }
  }
  val system = ActorSystem()
  val api: WAPI = TypedActor(system).typedActorOf(TypedProps[WAPIImpl]())
//  val api: WAPI = new WAPIImpl()

  api.getWeatherNowPlease().map{ println }
  api.getWeather().map{ println }
  println("hoge")
  system.shutdown
}

