package com.talk.randomTalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

    @GetMapping("community/home")
    public String home(){

        return "community/home";
    }

    @GetMapping("community/write")
    public String write(){

        return "community/write";
    }
}
