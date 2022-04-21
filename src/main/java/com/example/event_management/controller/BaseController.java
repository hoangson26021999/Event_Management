package com.example.event_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BaseController {

    @RequestMapping(value = {"/" , "/home"} )
    public String index() {
        return "home" ;
    }

    @RequestMapping("/content")
    public String content() {
        return "content" ;
    }

}
