/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import com.alibaba.fastjson.JSON;
import com.fpx.pds.domain.Pkg;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2018/12/10 10:01
 * @version: v1.0.0
 */
public class TestDemo {

    private final int UNIT_MB = 1024 * 1024;

    @Test
    public void test1() throws InterruptedException {
        /*List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add("字符串" + i);
        }*/
        List<Pkg> list = new ArrayList<>();
        Pkg pkg = null;
        for (int i = 0; i < 100000; i++) {
            pkg = new Pkg();
            pkg.setShippingNo("包裹编号：" + i);
            pkg.setInBoxTime(new Date());
            list.add(pkg);
        }
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        maxMemory = maxMemory / UNIT_MB;
        System.out.println("最大内存：" + maxMemory + "MB");
        totalMemory = totalMemory / UNIT_MB;
        System.out.println("总内存：" + totalMemory + "MB");

        usedMemory = usedMemory / UNIT_MB;
        System.out.println("已用内存：" + usedMemory + "MB");

        System.out.println("剩余可内存：" + freeMemory / UNIT_MB + "MB");

        Thread.sleep(10000);
    }

    @Test
    public void test2() {
        try {
            System.out.println("业务执行完了");
            if (1 == 1) return;
            System.out.println("234234");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("是否被执行了");
        }
    }

    @Test
    public void test3() {
        String jsonString = JSON.toJSONString(null);
        System.out.println(jsonString);
    }

    @Test
    public void test4() {
        Pkg pkg = null;
        if (pkg != null && pkg.getInBoxTime() != null) {
            System.out.println(pkg.getInBoxTime());
        }
    }
}
