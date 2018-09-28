package com.baozun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baozun.service.IUserService;

import redis.clients.jedis.JedisCluster;

@Component
public class UserServiceImpl implements IUserService {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String findRedis() {

        jedisCluster.set("userName", "hello wenqy");
        return jedisCluster.get("userName");
    }

}
