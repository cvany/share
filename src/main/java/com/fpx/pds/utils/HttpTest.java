/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.utils;


/**
 * @Author: cuiwy
 * @Date: 2018/12/12 18:00
 * @version: v1.0.0
 */
public class HttpTest {

    public static void main(String[] args){
        String entity = HttpUtil.get("http://www.baidu.com", null);
        System.out.println(entity);
    }
}
