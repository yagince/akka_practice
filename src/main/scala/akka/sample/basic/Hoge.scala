package akka.sample.basic

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 2013/02/16
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
object Hoge extends App {
  for {
    hoge <- Some(1)
    foo <- Some(1)
  } yield {
    println(hoge, foo)
  }
}
