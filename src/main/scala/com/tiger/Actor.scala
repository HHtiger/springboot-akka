package com.tiger

import akka.actor.{Actor, ActorRef, ActorSystem, DeadLetter, PoisonPill, Props}
import akka.event.Logging
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._
import scala.util.control.NonFatal
import scala.util.{Failure, Random, Success}
import scala.concurrent.ExecutionContext.Implicits.global

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