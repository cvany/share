/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import com.fpx.pds.api.SysApi;
import com.fpx.pds.utils.AlgoriUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: cuiwy
 * @Date: 2019/1/7 14:23
 * @version: v1.0.0
 */
public class AlgoriTest {

    private String lng;
    private String lat;
    private String json;

    @Before
    public void before() {
        lng = "113.84629179644324";
        lat = "22.62962382";
        json = "[{\"lng\":113.813652,\"lat\":22.635371},{\"lng\":113.841751,\"lat\":22.647312},{\"lng\":113.851165,\"lat\":22.637172},{\"lng\":113.854902,\"lat\":22.633036},{\"lng\":113.858999,\"lat\":22.626131},{\"lng\":113.870389,\"lat\":22.610051},{\"lng\":113.868916,\"lat\":22.608483},{\"lng\":113.867443,\"lat\":22.606948},{\"lng\":113.864317,\"lat\":22.607615},{\"lng\":113.860113,\"lat\":22.606581},{\"lng\":113.856735,\"lat\":22.603778},{\"lng\":113.844123,\"lat\":22.590131},{\"lng\":113.841104,\"lat\":22.592934},{\"lng\":113.835211,\"lat\":22.599875},{\"lng\":113.827738,\"lat\":22.611485},{\"lng\":113.822707,\"lat\":22.619225},{\"lng\":113.818539,\"lat\":22.625897}]";
    }

    /**
     * 目前系统使用的匹配算法
     */
    @Test
    public void testSystemAlgori() {
        long s = System.currentTimeMillis();
        System.out.println(SysApi.convertAndCheck(lng, lat, json));
        long e = System.currentTimeMillis();
        System.out.println("目前系统所用算法 耗时：" + (e - s) + "ms");
    }

    /**
     * 新匹配算法
     */
    @Test
    public void testNewAlgori() {
        long s = System.currentTimeMillis();
        System.out.println(AlgoriUtil.isInPolygon(lng, lat, json));
        long e = System.currentTimeMillis();
        System.out.println("新算法耗时：" + (e - s) + "ms");
    }
}
