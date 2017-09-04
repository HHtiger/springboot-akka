package com.tiger.controller;

import com.tiger.com.tiger.PicServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @Autowired
    private PicServer picServer;

    @RequestMapping("/getPic")
    @ResponseBody
    public String getPic() {
        return picServer.getPic();
    }
}