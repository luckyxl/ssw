package com.aas.ssw.business.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @GetMapping("/admin")
    @ResponseBody
    public String test1(){
        return "haha";
    }
    @GetMapping("/user")
    @ResponseBody
    public String test2(){
        return "heihei";
    }


}
