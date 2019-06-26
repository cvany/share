/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: cuiwy
 * @Date: 2019/3/29 15:39
 * @version: v1.0.0
 */
public class CollectionTest {
    @Test
    public void test1() {
        TreeMap map = new TreeMap();
        map.put("", "");
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (String s : list) {
                System.out.println("这是空集合");
            }
        }

        System.out.println("结束");
    }

    @Test
    public void test3() {
        String s = "Sdf32423f";
        String ss = "SDF32423F";
        String sss = "{\n" +
                "\t\t\"status\":\"valid\",\n" +
                "\t\t\"supportedBusiness\":\"PDS\",\n" +
                "\t\t\"warehouseCode\":\"CNHKGF\"\n" +
                "\t}";
        System.out.println(s.equalsIgnoreCase(ss));

        Date date = new Date(1557025036000L);
        System.out.println(date);
    }

    @Test
    public void test4() {
        Stack stack =new Stack();
        stack.peek();
        LinkedList linkedList =new LinkedList();
    }
}
