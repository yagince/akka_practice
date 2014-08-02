package akka.sample.supervisor

import akka.actor._
import akka.actor.SupervisorStrategy.{Stop, Restart, Resume, Escalate}
import scala.concurrent.duration._
import scala.util.Random
import akka.actor.OneForOneStrategy
import akka.pattern.ask
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import akka.util.Timeout

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2014/07/30
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
object Supervisor01 extends App {
  val system = ActorSystem()
  val actor = system.actorOf(Props[SupervisorActor])
  implicit val timeout = Timeout(10.seconds)
  try {
    (actor ? Props[Worker]).mapTo[ActorRef].map{ w =>
      w ! 0
    }
    Thread.sleep(5000);
  } finally {
    system.shutdown
  }
}

class SupervisorActor extends Actor {

  override def supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 500 millisecond) {
      case _: ArithmeticException      => Resume
      case _: NullPointerException     => Restart
      case _: IllegalArgumentException => Stop
    }

  def receive = {
    case msg: Props => sender ! context.actorOf(msg)
  }

  override def preRestart(cause: Throwable, msg: Option[Any]) {
    println("pre Restart")
  }
}

class Worker extends Actor {
  
  def receive = {
    case msg =>
      println(s"receive $msg")
      msg match {
        case 0 => throw new ArithmeticException
        case 1 => throw new NullPointerException
        case 2 => throw new IllegalArgumentException
        case _ => throw new Exception
      }
  }
}
