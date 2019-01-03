/*
 * Copyright (c) 2016 4PX Information Technology Co.,Ltd. All rights reserved.
 */
package com.fpx.pds.api;

/**
 * 数据库区域表保存的经纬度
 *
 * @author wanghui
 * @date 2016-08-18
 * @since v1.1.0
 */
public class PickupPoint {
    private Double lng;//经度
    private Double lat;//纬度

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }


}
