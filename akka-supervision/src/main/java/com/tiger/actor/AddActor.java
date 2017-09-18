package com.tiger.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.tiger.exception.AdderException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tiger on 2017/9/8.
 */
public class AddActor extends AbstractActor {

    final LoggingAdapter log = Logging.getLogger(context().system().eventStream(), AddActor.class);

    private static AtomicInteger adder = new AtomicInteger();

    @Override
    public void preStart() throws Exception {
        log.warning("preStart");
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        log.warning("postRestart:{}", reason);
        self().tell(2, ActorRef.noSender());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, i -> {
                    if (i == 2) {
                        throw new AdderException("2 is eval");
                    }
                    log.debug("{}", adder.addAndGet(i));
                })
                .build();
    }
}
