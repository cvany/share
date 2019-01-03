/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

/**
 * @Author: cuiwy
 * @Date: 2018/12/17 17:01
 * @version: v1.0.0
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread("MyThread");
        t.start();
        Thread.sleep(50);// 睡眠100毫秒
        t.interrupt();// 中断t线程
    }
}

class MyThread extends Thread {
    int i = 0;

    public MyThread(String name) {
        super(name);
    }

    public void run() {
        try {
            while (true) {
                System.out.println(getName() + getId() + "执行了" + ++i + "次");
                Thread.sleep(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

