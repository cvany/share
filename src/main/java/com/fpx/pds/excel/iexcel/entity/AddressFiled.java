
package com.fpx.pds.excel.iexcel.entity;

import com.github.houbb.iexcel.annotation.ExcelField;
import lombok.Data;

/**
 * @Author: cuiwy
 * @Date: 2019/7/24 14:24
 * @version: v1.0.0
 */
@Data
public class AddressFiled {

    @ExcelField
    private String address;
    @ExcelField
    private String targetAddress;
    @ExcelField
    private String lng;
    @ExcelField
    private String lat;
}
