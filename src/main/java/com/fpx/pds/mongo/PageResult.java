/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/1/28 17:05
 * @version: v1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResult<T> {
    @ApiModelProperty("页码，从1开始")
    private Integer pageNum;

    @ApiModelProperty("页面大小")
    private Integer pageSize;

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("总页数")
    private Integer pages;

    @ApiModelProperty("数据")
    private List<T> list;
}
