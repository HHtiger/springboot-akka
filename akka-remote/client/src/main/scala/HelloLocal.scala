import java.util.UUID

import akka.actor._
import com.tiger.model.PigOuterClass.Pig

object Local extends App {

  implicit val system = ActorSystem("LocalSystem")
  val localActor = system.actorOf(Props[LocalActor], name = "LocalActor")  // the local actor
  for( i <- 1 to 1000){
    localActor ! Pig.newBuilder().setId(UUID.randomUUID().toString).build()
  }

}

class LocalActor extends Actor {

  val remote = context.actorSelection("akka.tcp://HelloRemoteSystem@127.0.0.1:5150/user/RemoteActor")
  var counter = 0

  def receive = {
    case ball:Ball =>
      remote ! ball
    case count: Int =>
      println(s"total is : $count")
    case pig : Pig =>
      remote ! pig
  }
}