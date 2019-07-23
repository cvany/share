package com.fpx.pds.excel.iexcel;

import com.github.houbb.iexcel.annotation.ExcelField;
import lombok.Data;

/**
 * @Author: cuiwy
 * @Date: 2019/7/3 17:24
 * @version: v1.0.0
 */
@Data
public class ExcelFieldModel {

    @ExcelField
    private String routeName;
    @ExcelField
    private String nodeCode;
    @ExcelField
    private String nodeName;
    @ExcelField
    private String address;
    @ExcelField
    private String longitude;
    @ExcelField
    private String latitude;
}
