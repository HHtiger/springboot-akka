package com.tiger;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.tiger.actor.ManagerActor;

/**
 * Created by tiger on 2017/9/8.
 */
public class Main {

    public static void main(String args[]){

        ActorSystem system = ActorSystem.create();
        ActorRef manager = system.actorOf(Props.create(ManagerActor.class));
        manager.tell("start",ActorRef.noSender());

    }

}
