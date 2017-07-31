package com.tiger.controller;

import akka.actor.ActorSystem;
import com.tiger.GetPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @Autowired
    private ActorSystem actorSystem;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return GetPic.get(actorSystem);
    }
}