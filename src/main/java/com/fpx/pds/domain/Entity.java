/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: cuiwy
 * @Date: 2019/2/11 15:20
 * @version: v1.0.0
 */
@Data
@Component
public class Entity {
    @Value("Jack")
    private String name;

    @Value("#{ T(java.lang.Math).random()*100}")
    private Integer age;

    @Value("#{systemProperties['os.name']}")
    private String osName;
}
