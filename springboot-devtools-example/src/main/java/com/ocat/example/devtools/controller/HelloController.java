package com.ocat.example.devtools.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shikui@tiduyun.com
 * @date 2023/5/10
 */
@RestController("/")
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        return "echo1";
    }
}