package com.tiger;

import akka.actor.ActorSystem;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetPicTest {

    private ActorSystem actorSystem;

    @BeforeTest
    public void before(){
        actorSystem = ActorSystem.create("actorSystem");
    }

    @Test(threadPoolSize = 12,invocationCount = 1000)
    public void test(){
        GetPic.get(actorSystem);
    }

    @AfterTest
    public void after(){
        System.out.printf("total time : %d s",actorSystem.uptime());
    }

}