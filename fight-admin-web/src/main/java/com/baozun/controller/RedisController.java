package com.baozun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baozun.service.IUserService;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/find")
    public String findRedis() {

        return userService.findRedis();
    }
}
