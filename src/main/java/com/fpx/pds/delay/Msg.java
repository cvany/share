/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.delay;

import lombok.Data;

/**
 * @Author: cuiwy
 * @Date: 2018/12/12 09:39
 * @version: v1.0.0
 */
@Data
public class Msg implements Runnable {
    private Object data;
    Thread timerThread;
    private Long second;

    public Msg(Object data, long second) {
        this.data = data;
        this.second = second;
        this.timerThread = new Thread(this);
        timerThread.start();
    }

    @Override
    public void run() {
        try {
            timerThread.sleep(second);
            System.out.println(this.data + " ：" + this.second / 1000 + " 秒 ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long cal(long source, long unit) {
        return source / unit;
    }
}
