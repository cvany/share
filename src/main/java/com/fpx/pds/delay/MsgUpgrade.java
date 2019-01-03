/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.delay;

import com.fpx.pds.utils.TimeUtil;

/**
 * @Author: cuiwy
 * @Date: 2018/12/13 16:22
 * @version: v1.0.0
 */
public class MsgUpgrade {
    /**
     * 消息内容
     */
    private Object data;
    /**
     * 定时
     */
    private long timing;

    public MsgUpgrade(Object data, long timing) {
        this.data = data;
        this.timing = timing;
        timerTask.start();
    }

    /**
     * 计时器
     */
    private Thread timerTask = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                timerTask.sleep(timing);
                String value = String.valueOf(data);
                if (value.length() < 9) {
                    int len = value.length();
                    while (9 - len > 0) {
                        value = value.concat(" ");
                        len = value.length();
                    }
                }
                System.out.println(value + "定时：" + TimeUtil.comsumeTime(timing));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
