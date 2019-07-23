package com.fpx.pds.share;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/6/27 18:18
 * @version: v1.0.0
 */
public class BigDataTest {

    public static void main(String[] args) {
        System.out.println("free:" + Runtime.getRuntime().freeMemory() / 1024
                / 1024);
        System.out.println("total:" + Runtime.getRuntime().totalMemory() / 1024
                / 1024);
        System.out.println("max:" + Runtime.getRuntime().maxMemory() / 1024
                / 1024);
        System.out.println("=============");
        long t = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        String[] aaa = new String[2000000];
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println("=============");
        try {
            Thread.sleep(3000);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        for (int i = 0; i < 2000000; i++) {
            aaa[i] = new String("aaa");
        }
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println("=============");
        try {
            Thread.sleep(3000);
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        System.out.println("################################");
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println(maxMemory / (1024 * 1024));
        long l = Runtime.getRuntime().freeMemory();
        System.out.println(l);
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 1000000; i++) {
            list.add("sdfsdfsdf适当放松的地对地导弹地对地导弹地对地导弹地对地导弹地对地导弹" + i);
        }

        long back = Runtime.getRuntime().freeMemory();
        System.out.println((back - l) / 1000000);
        System.out.println(list.size());
    }

    @Test
    public void test1() {
        List<String> al = new ArrayList<>();
        al.add("1");
        al.add("2");
        al.add("3");
        al.add("4");
        for (int i = 0; i < al.size(); i++) {
            System.out.println("$$" + al.size());
            if ("2".equals(al.get(i))) {
                String remove = al.remove(i);
                System.out.println("remove value：" + remove);
            }
            System.out.println(al.get(i));
        }
        System.out.println("******************************");
        List<String> stringList = null;
        LinkedList<String> list = new LinkedList<>();

        list.addFirst("1");
        list.addLast("2");
        list.addFirst("00");
        list.addFirst("1111");
        stringList = list;
        for (int i = 0; i < list.size(); i++) {
            if ("1111".equals(list.get(i))) {
                stringList.remove(i);
            }
            System.out.println(list.get(i));
        }
        System.out.println("=============================");
        for (String s : stringList) {
            System.out.println(s);
        }
    }

    @Test
    public void test2() {
    }
}
