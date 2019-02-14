package com.fpx.pds.redis;

import com.fpx.pds.ShareApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author: cuiwy
 * @Date: 2019/2/14 13:56
 * @version: v1.0.0
 */
public class IRedisUtilTest extends ShareApplicationTests {

    @Autowired
    IRedisUtil redisUtil;

    @Test
    public void set() {
        boolean set = redisUtil.set("com:pds:cache:name", "刘亦菲", 60L);
        System.out.println(set);
    }
}