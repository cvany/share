
package com.fpx.pds.delay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: cuiwy
 * @Date: 2018/12/12 09:43
 * @version: v1.0.0
 */
public class MsgTest {

    public static void main(String[] args) {
        List<Msg> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int anInt = random.nextInt(10000);
            Msg msg = new Msg("消息编码" + i, i * anInt);
            list.add(msg);
        }
      /*  long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long unit = 1024 * 1024;
        System.out.println("一万消息占用内存：" + (totalMemory - freeMemory) / unit + "M");*/
    }
}
