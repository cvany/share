/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.excel;


import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2018/12/12 16:52
 * @version: v1.0.0
 */
public class ExcelTest {

    public static void main(String[] args) {
        String filePath = "D:\\handoverListModel.xlsx";
        List<Object[]> list = ExcelUtil.resolveExcel(filePath);
        System.out.println(list.size());
        String str = (String) list.get(0)[5];
        String[] replaceAll = str.replaceAll("；|;|\\\\n", ",").split(",");
        for (int i = 0; i < replaceAll.length; i++) {
            String[] trim = replaceAll[i].trim().replaceAll(" +", ",").split(",");
            for (int j = 0; j < trim.length; j++) {
                System.out.println(trim[j]);
            }
        }

    }


}
