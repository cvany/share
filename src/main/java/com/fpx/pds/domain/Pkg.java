
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
