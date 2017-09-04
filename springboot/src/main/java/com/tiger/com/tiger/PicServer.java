package com.tiger.com.tiger;

import akka.actor.ActorSystem;
import com.tiger.GetPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicServer {

    @Autowired
    private ActorSystem actorSystem;

    public String getPic(){
        return GetPic.get(actorSystem);
    }

}
