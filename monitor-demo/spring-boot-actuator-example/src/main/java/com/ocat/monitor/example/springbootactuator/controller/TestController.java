package com.ocat.monitor.example.springbootactuator.controller;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年04月13日 18:33:58
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/user")
    public String getInfo(@RequestParam String userId ){
        log.info("userId:{}",userId);
        return "123";
    }

    @GetMapping("/app")
    public String getAppInfo(@RequestParam String appId ){
        log.info("appId:{}",appId);
        return "123456";
    }

    @GetMapping("/user/app")
    public String getUserAppInfo(@RequestParam String appId ,@RequestParam String userId ){
        log.info("appId:{}",appId);
        log.info("userId:{}",userId);
        return "abc";
    }
}
