/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: cuiwy
 * @Date: 2019/3/19 09:45
 * @version: v1.0.0
 */
public class ThreadTest {

    @Test
    public void test1() {
        FutureTask futureTask = new FutureTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        try {
            Object o = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        //java 7  当天零点
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        System.out.println(start);
        //当天 24点
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        start = calendar.getTime();
        System.out.println(start);
    }

    @Test
    public void test3() {
        //java8 方式
        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);//当天零点
        String yyyyMMddHHmmss = today_end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(yyyyMMddHHmmss);
    }

    @Test
    public void test4() {
        Date dt = new Date();

        String year = String.format("%tY", dt);
        String mon = String.format("%tm", dt);
        String day = String.format("%td", dt);
        String morningOrAfternoon = String.format("%tp", dt);

        String m0 = String.format("%tR", dt);
        System.out.println(m0);
        System.out.println(morningOrAfternoon);

        System.out.println(year);
        System.out.println(mon);
        System.out.println(day);
        System.out.println("=================");
        Date date =new Date(1551715200000L);
        String dayNotZero = String.format("%te", date);
        int anInt = Integer.parseInt(dayNotZero);
        System.out.println(anInt);


    }
}
