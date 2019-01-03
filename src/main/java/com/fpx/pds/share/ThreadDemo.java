/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

/**
 * @Author: cuiwy
 * @Date: 2018/11/27 18:09
 * @version: v1.0.0
 */
public class ThreadDemo implements Runnable {
    int b = 1;

    synchronized void m1() throws InterruptedException {
        b = 1000;
//        Thread.sleep(500);
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
//        Thread.sleep(250);
        b = 2000;
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<1;i++){
            ThreadDemo thread = new ThreadDemo();
            Thread thread1 = new Thread(thread);
            thread1.start();
            thread.m2();
            System.out.println("main Thread:b=" + thread.b);
        }
    }
}
