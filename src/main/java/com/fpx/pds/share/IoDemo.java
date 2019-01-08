
package com.fpx.pds.share;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: cuiwy
 * @Date: 2018/10/9 09:50
 * @version: v1.0.0
 */
public class IoDemo {

    @Test
    public void io() throws FileNotFoundException {
        File file = new File("C:\\Users\\cuiwy.4PXAD\\Desktop\\luck.tx");
        FileInputStream fileInputStream = new FileInputStream(file);
//        FileOutputStream fileOutputStream =new FileOutputStream();
    }

    @Test
    public void test1() {
        String s = "GPP00000000000002330288";
        System.out.println(s.length());
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            if (i % 2 == 0)
                System.out.print(i + " ");
        }
        long end = System.currentTimeMillis();
        System.out.println("================================");
        System.out.println("耗时：" + (end - start) + "ms");
    }

    @Test
    public void testDate() {
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date source = dd.parse("2018/11/3 11:29:54");
            Date target = dd.parse("2018/11/6 11:30:54");
            long l = target.getTime() - source.getTime();
            int i = 24 * 3600 * 1000;
            System.out.println(l / i);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.clear();
            list.add("测试" + i);
        }
        System.out.println(list.toString());
    }

    @Test
    public void test3() {
        int a = 4;
        a = a >> 1;
        System.out.println(a);
    }

    @Test
    public void test4() {
        Calendar calendar = Calendar.getInstance();
        Date date =new Date();
        calendar.setTime(date);
        for (int i=2;i>=0;i--){
            calendar.add(Calendar.SECOND, -((i + 1) * 10));
            System.out.println(calendar.getTime());
            calendar.setTime(date);
        }
    }

    @Test
    public void test5() {
        Map map =new HashMap();
        map.put("receiveTime","1543940519665");
        Object obj = map.get("receiveTime");
        Date defaultDateValue = getDefaultDateValue(obj, new Date());
        System.out.println(defaultDateValue);
    }

    public  Date getDefaultDateValue(Object obj, Date date){
        if (obj instanceof Long) {
            System.out.println("是long类型："+obj);
            return new Date((Long)obj);
        }else if(obj instanceof Date){
            return (Date)obj;
        }else {
            return date;
        }
    }

}
