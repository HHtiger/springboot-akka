package com.tiger

import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

object Print{
  val count = new AtomicInteger(0)
}

class Print extends Actor with ActorLogging{

  override def receive = {

    case (v1:ActorRef,v2:ActorRef) =>
      for(i <- 1 to 5){
        log.debug("{}",Print.count.incrementAndGet())
        TimeUnit.MILLISECONDS.sleep(100)
      }
      if(Print.count.get()<75){
        v1 ! (v2,self)
      }
  }

}


object aaa {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem.create()
    val v1 = system.actorOf(Props[Print].withDispatcher("print-dispatcher"))
    val v2 = system.actorOf(Props[Print].withDispatcher("print-dispatcher"))
    val v3 = system.actorOf(Props[Print].withDispatcher("print-dispatcher"))
    v1 ! (v2,v3)
  }

}
