/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.algori;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2018/12/29 13:37
 * @version: v1.0.0
 */
public class PageNum {

    public static void main(String[] args) {
        System.out.println(pageNum(9900).toString());
    }

    /**
     * 要求：每次分页大小不能超过5000，分页最多不超过10次
     * 正常值设置为3000，最大阈值为5000
     *
     * @param number
     * @return
     */
    public static Map<String, Integer> pageNum(int number) {
        if (number <= 0) return null;
        Map<String, Integer> map = new HashMap(2);
        if (number < 10000) {
            map.put("pageSize", 3000);
            map.put("pageNum", number / 3000 + 1);
        } else if (number < 50000) {
            map.put("pageSize", 4000);
            map.put("pageNum", number / 4000 + 1);
        } else {
            map.put("pageSize", 5000);
            map.put("pageNum", number / 5000 + 1);
        }
        return map;
    }
}
