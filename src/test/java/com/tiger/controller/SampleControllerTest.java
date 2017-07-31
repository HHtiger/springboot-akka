package com.tiger.controller;

import akka.actor.ActorSystem;
import com.tiger.GetPic;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by tiger on 2017/7/31.
 */
public class SampleControllerTest {

    private ActorSystem actorSystem;

    @BeforeTest
    public void before(){
        actorSystem = ActorSystem.create("actorSystem");
    }

    @Test(threadPoolSize = 12,invocationCount = 50000)
    public void test(){
        GetPic.get(actorSystem);
    }

    @AfterTest
    public void after(){
        System.out.printf("total time : %d s",actorSystem.uptime());
    }


}