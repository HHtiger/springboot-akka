package com.tiger

import akka.actor.{ActorRef, ActorSystem, PoisonPill, Props}
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
  * Created by tiger on 2017/7/31.
  */
object GetPic {
  def get(system: ActorSystem) : String={
    val big: ActorRef = system.actorOf(Props[GetPicActor])
    val small: ActorRef = system.actorOf(Props[GetPicActor])

    implicit val timeout = Timeout(3 seconds)

    val fb = big ? BigPic("b1")
    val fs = small ? SmallPic("b2")

    val f3 = Seq(fb, fs)
    val f4 = Future.firstCompletedOf(f3)

    f4.onComplete {
      case Success(r) =>
        println(r)
      case Failure(NonFatal(e)) =>
        println("cannot get,timeout")
    }

    big ! PoisonPill
    small ! PoisonPill

    Await.result(f4, 3 seconds).asInstanceOf[String]
  }
}
