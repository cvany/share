/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.redis;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cuiwy
 * @Date: 2019/2/2 10:27
 * @version: v1.0.0
 */
@Data
public class User implements Serializable {
    private int id;

    private String name;

    private int age;
}
