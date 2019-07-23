package com.fpx.pds.map.address;

import lombok.Getter;

/**
 * 地址分段节点
 *
 * @Author: cuiwy
 * @Date: 2019/7/19 16:11
 * @version: v1.0.0
 */
public enum EnumAreaNode {
    country("国"),
    province("省"),
    city("市"),
    area("区"),
    street("街"),
    path("道"),
    road("路"),
    lane("巷"),
    number("号"),
    garden("园"),
    building("厦");
    @Getter
    private String cnArea;

    EnumAreaNode(String cnArea) {
        this.cnArea = cnArea;
    }
}
