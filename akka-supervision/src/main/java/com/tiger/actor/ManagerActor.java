package com.tiger.actor;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import com.tiger.exception.AdderException;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by tiger on 2017/9/8.
 */
public class ManagerActor extends AbstractActor {

    final LoggingAdapter log = Logging.getLogger(context().system().eventStream(), ManagerActor.class);

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(5, Duration.create(5, TimeUnit.SECONDS),
                    DeciderBuilder.match(AdderException.class, e -> SupervisorStrategy.restart())
                            .matchAny(o -> SupervisorStrategy.escalate())
                            .build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

//    @Override
//    public void preRestart(Throwable cause, Optional<Object> msg) {
//        // do not kill all children, which is the default here
//    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("start", s -> {
                    for (int i = 0; i < 3; i++) {
                        ActorRef adder = context().actorOf(Props.create(AddActor.class).withDispatcher("default-dispatcher"));
                        ActorRef except = context().actorOf(Props.create(ExcetionActor.class).withDispatcher("default-dispatcher"));
                        adder.tell(i, ActorRef.noSender());
                    }
                }).build();
    }
}
