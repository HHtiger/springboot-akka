import java.util.concurrent.atomic.AtomicInteger

import akka.actor._

object HelloRemote extends App  {
  val system = ActorSystem("HelloRemoteSystem")
  val remoteActor = system.actorOf(Props[RemoteActor], name = "RemoteActor")
  remoteActor ! "The RemoteActor is alive"
}

class RemoteActor extends Actor {

  var count  = new AtomicInteger(0)

  def receive = {
    case msg: String =>
      println(s"RemoteActor received message '$msg'")
    case ball: Ball =>
      println(ball.id)
      sender() ! count.incrementAndGet()
  }
}