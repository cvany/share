/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @Author: cuiwy
 * @Date: 2019/2/1 10:50
 * @version: v1.0.0
 */
@Data
public class BaseVO extends Query {
    private Integer pageNum;
    private Integer pageSize;
}
