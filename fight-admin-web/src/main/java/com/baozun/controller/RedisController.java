package com.baozun.controller;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baozun.common.ResultResponse;
import com.baozun.dao.entity.Student;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;

    //    @Autowired
    //    private IUserService userService;

    // @RequestMapping("/find")
    //    public String findRedis() {
    //        return userService.findRedis();
    //}
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResultResponse save(@RequestBody Student student) {

        ResultResponse resultResponse = new ResultResponse();
        redisTemplate.opsForValue().set("save", "国庆", 30 * 60, TimeUnit.SECONDS);
        String value = redisTemplate.opsForValue().get("save");
        //set("save", "国庆");
        //resultResponse.setData(value);
        resultResponse.setData(value);
        return resultResponse;
    }

    public boolean set(final String key, String value) {

        boolean result = false;

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        result = true;

        return result;
    }
}
