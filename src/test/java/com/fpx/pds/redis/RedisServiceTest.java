package com.fpx.pds.redis;

import com.alibaba.fastjson.JSON;
import com.fpx.pds.ShareApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author: cuiwy
 * @Date: 2019/2/2 10:28
 * @version: v1.0.0
 */
public class RedisServiceTest extends ShareApplicationTests {

    @Autowired
    IRedisService iRedisService;

    @Test
    public void test_set_string() {
        iRedisService.set("key1", "第一条缓存值");
        String key1 = iRedisService.get("key1");
        System.out.println(key1);
    }

    @Test
    public void test_set_obj() {
        User user = new User();
        user.setAge(33);
        user.setId(2);
        user.setName("罗志祥");
        iRedisService.set("user2", user);
        String user1 = iRedisService.get("user2");
        User user2 = JSON.parseObject(user1, User.class);
        System.out.println(user2.toString());
    }
}