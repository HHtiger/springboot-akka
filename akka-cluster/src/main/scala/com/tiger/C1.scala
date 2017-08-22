package com.tiger

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by tiger on 2017/8/21.
  */
object C1 {
  def main(args: Array[String]): Unit = {

    Seq(2551,2552,2553) foreach { port =>
      val system = ActorSystem("ClusterSystem", config(port))
      system.actorOf(Props[TigerServer])
    }

  }

  def config(port:Int) : Config={
    val config = ConfigFactory
      .parseString(s"akka.remote.netty.tcp.port=" + port)
      .withFallback(ConfigFactory.parseString("akka.cluster.roles = [tigerServer]"))
      .withFallback(ConfigFactory.load())
    config
  }

}
