/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import com.fpx.pds.api.SysApi;
import com.fpx.pds.excel.ExcelUtil;
import com.fpx.pds.utils.AlgoriUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/1/7 19:22
 * @version: v1.0.0
 */
public class ShapeTest {

    private List<Object[]> pointList;
    private List<Object[]> pls;

    @Before
    public void before() {
        String path = "D:\\point.xlsx";
        String filePath = "D:\\pl.xlsx";
        pointList = ExcelUtil.resolveExcel(path);
        pls = ExcelUtil.resolveExcel(filePath);
    }

    @Test
    public void testNewAlgori() {
        double trueCount = 0;
        double falseCount = 0;
        StringBuilder sb = new StringBuilder();
        System.out.println("使用新算法进行匹配，总数" + pointList.size());
        for (Object[] point : pointList) {
            boolean flag = false;
            for (Object[] pl : pls) {
                String s = pl[0] + "";
                if (s.contains("object")) continue;
                if (AlgoriUtil.isInPolygon(point[0] + "", point[1] + "", s)) {
                    flag = true;
                    trueCount++;
                    break;
                }
            }
            if (!flag) {
                falseCount++;
                sb.append("{" + point[0] + "," + point[1] + "},");
            }
        }
        System.out.println("匹配成功：" + trueCount + "  成功百分比：" + (trueCount / pointList.size()) * 100);
        System.out.println("匹配失败：" + falseCount + "  失败百分比：" + (falseCount / pointList.size()) * 100);
        System.out.println("未匹配成功经纬度：" + sb.toString());
    }

    @Test
    public void testSystemAlgori() {
        double trueCount = 0;
        double falseCount = 0;
        StringBuilder sb = new StringBuilder();
        StringBuilder trueSb = new StringBuilder();
        System.out.println("使用新算法进行匹配，总数" + pointList.size());
        for (Object[] point : pointList) {
            boolean flag = false;
            for (Object[] pl : pls) {
                String s = pl[0] + "";
                if (s.contains("object")) continue;
                if (SysApi.convertAndCheck(point[0] + "", point[1] + "", s)) {
                    flag = true;
                    trueCount++;
                    trueSb.append("{" + point[0] + "," + point[1] + "},");
                    break;
                }
            }
            if (!flag) {
                falseCount++;
                sb.append("{" + point[0] + "," + point[1] + "},");
            }
        }
        System.out.println("匹配成功：" + trueCount + "  成功百分比：" + (trueCount / pointList.size()) * 100);
        System.out.println("匹配失败：" + falseCount + "  失败百分比：" + (falseCount / pointList.size()) * 100);
        System.out.println("未匹配成功经纬度：" + sb.toString());
        System.out.println("匹配成功经纬度：" + trueSb.toString());
    }
}
