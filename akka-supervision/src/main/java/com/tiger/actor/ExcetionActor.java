package com.tiger.actor;

import akka.actor.AbstractActor;
import com.tiger.exception.AdderException;

/**
 * Created by tiger on 2017/9/8.
 */
public class ExcetionActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("error",s->{
                    throw new AdderException("");
                })
                .build();
    }
}
