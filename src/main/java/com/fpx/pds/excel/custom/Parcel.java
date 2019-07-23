package com.fpx.pds.excel.custom;

import lombok.Data;

/**
 * 包裹实体
 *
 * @Author: cuiwy
 * @Date: 2019/7/10 15:26
 * @version: v1.0.0
 */
@Data
public class Parcel {
    public Parcel() {
    }

    public Parcel(String shippingNo, Double weight, String finishTime) {
        this.shippingNo = shippingNo;
        this.weight = weight;
        this.finishTime = finishTime;
    }

    private String shippingNo;
    private Double weight;
    private String finishTime;

}
