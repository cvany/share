
package com.fpx.pds.share;

import com.fpx.pds.ShareApplicationTests;
import com.fpx.pds.domain.Entity;
import com.fpx.pds.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Author: cuiwy
 * @Date: 2018/12/13 08:55
 * @version: v1.0.0
 */
@Slf4j
public class DemoTest extends ShareApplicationTests {

    @Test
    public void test1() {
        int i = 55901 / 3600;
        System.out.println(i);
    }

    @Test
    public void test2() {
        System.out.println(TimeUtil.comsumeTime(69755 * 1000));
        System.out.println(TimeUtil.comsumeTime(70181 * 1000));
        System.out.println(TimeUtil.comsumeTime(75162 * 1000));
        System.out.println(TimeUtil.comsumeTime(79947 * 1000));
        System.out.println(TimeUtil.comsumeTime(84129 * 1000));
        System.out.println(TimeUtil.comsumeTime(99810 * 1000));
    }

    @Test
    public void test3() {
        System.out.println(updateLong2Date("15646749874616"));
    }

    public static Date updateLong2Date(String time) {
        Date date = null;
        try {
            Long value = Long.valueOf(time);
            date = new Date(value);
        } catch (Exception e) {
            log.error("转换long类型时间异常：{},参数：{}", e, time);
        }
        if (null == date) {
            date = new Date();
        }
        return date;
    }

    @Test
    public void test4() {
        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        System.out.println(precision);
    }

    @Test
    public void test5() {
        try {
            int a = 1;
            if (1 == a) {
                int i = a / 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            System.out.println("这里还执行吗");
        }
    }

    @Test
    public void test6() {
        Map<String, Integer> map = new HashMap(2);
        int number = 1;
        map.put("pageSize", 3000);
        map.put("pageNum", 10 / 3000 + 1);
        System.out.println(map.get("pageNum"));
    }

    @Autowired
    Entity entity;

    @Test
    public void test7() {
        String name = entity.getName();
        System.out.println(name);
    }

    @Test
    public void test8() {
        Scanner scanner =new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);
//        ThreadPoolExecutor
    }
}
