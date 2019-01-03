/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author: cuiwy
 * @Date: 2018/12/10 10:28
 * @version: v1.0.0
 */
@Data
public class Pkg {
    private String shippingNo;
    private String status;
    private Date inBoxTime;
}
