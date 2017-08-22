package com.tiger

import akka.actor.Actor
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.{MemberEvent, MemberJoined, MemberUp}
import akka.event.Logging

/**
  * Created by tiger on 2017/8/21.
  */
class TigerServer extends Actor{

  val log = Logging(context.system, this)

  val cluster = Cluster(context.system)

  override def preStart(): Unit = cluster.subscribe(self, classOf[MemberEvent])
  override def postStop(): Unit = cluster.unsubscribe(self)

  override def receive: Receive = {
    case MemberUp(m) =>
      log.debug("tigerServer up : {}" , m.address.toString)
    case MemberJoined (m) =>
      log.debug("tigerServer join : {}" , m.address.toString)
    case _ =>
      log.debug("???")

  }
}
