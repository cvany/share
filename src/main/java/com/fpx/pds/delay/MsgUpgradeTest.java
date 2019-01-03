/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.delay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: cuiwy
 * @Date: 2018/12/13 16:57
 * @version: v1.0.0
 */
public class MsgUpgradeTest {

    public static void main(String[] args) throws InterruptedException {
        List<MsgUpgrade> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int anInt = random.nextInt(10000);
            MsgUpgrade msg = new MsgUpgrade("MSG_" + i, i * anInt);
            list.add(msg);
            Thread.sleep(50);
        }
    }
}
