package com.fpx.pds.redis;

import com.fpx.pds.ShareApplicationTests;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

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
        boolean set = redisUtil.set("com:pds:cache:name", "刘亦菲,Happy Valentine'Day", 60L);
        Object o = redisUtil.get("com:pds:cache:name");
        System.out.println(o);
    }

    @Test
    public void sset() {
        List list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(1);
        redisUtil.sAdd("com:pds:cache:list",list);
        Set<Object> objects = redisUtil.sGet("com:pds:cache:list");
        System.out.println(objects.toString());
    }
}