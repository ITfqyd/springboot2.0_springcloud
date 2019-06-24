package com.fqyd.springcloudeurekaclientb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

    @RequestMapping("/hello")
    public String testA() {
        return "Hello world BBBBBBBB!";
    }
}