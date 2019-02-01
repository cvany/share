/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.mongo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author: cuiwy
 * @Date: 2019/2/1 09:40
 * @version: v1.0.0
 */
@Data
public class User implements Serializable {
    private String name;
    private Integer age;
    private ArrayList<String> hobbies;
    private ArrayList<String> addr;
}
