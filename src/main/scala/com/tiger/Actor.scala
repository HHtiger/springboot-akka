package com.tiger

import akka.actor.{Actor, DeadLetter}
import akka.event.Logging

/**
  * Created by tiger on 2017/7/26.
  */

case class BigPic(name: String)

case class SmallPic(name: String)

class GetPicActor extends Actor {

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case big: BigPic =>
      log.debug("begin get big")
      //      Thread.sleep(Random.nextInt(10000))
//      Thread.sleep(2000)
      sender() ! "big ok"
    case small: SmallPic =>
      log.debug("begin get small")
      //      Thread.sleep(Random.nextInt(10000))
//      Thread.sleep(2000)
      sender() ! "small ok"
  }
}

class DeadLetterListener extends Actor {
  def receive = {
    case d: DeadLetter => println(d)
  }
}