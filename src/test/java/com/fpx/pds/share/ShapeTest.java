package com.fpx.pds.share;

import com.fpx.pds.api.SysApi;
import com.fpx.pds.excel.ExcelUtil;
import com.fpx.pds.utils.AlgoriUtil;
import org.junit.Before;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/1/7 19:22
 * @version: v1.0.0
 */
public class ShapeTest {

    private List<Object[]> pointList;
    private List<Object[]> shape;

    @Before
    public void before() {
        String poitPath = "D:\\point0108.xlsx";
        String shapePath = "D:\\pl.xlsx";
        pointList = ExcelUtil.resolveExcel(poitPath);
        shape = ExcelUtil.resolveExcel(shapePath);
    }

    @Test
    public void testNewAlgori() {
        double trueCount = 0;
        double falseCount = 0;
        StringBuilder sb = new StringBuilder();
        System.out.print("使用新算法进行匹配，总数" + pointList.size());
        long start = System.currentTimeMillis();
        for (Object[] point : pointList) {
            boolean flag = false;
            for (Object[] pl : shape) {
                String s = pl[0] + "";
                if (s.contains("object")) continue;
                if (AlgoriUtil.isInPg(point[0] + "", point[1] + "", s)) {
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
        long e = System.currentTimeMillis();
        System.out.println("   耗时" + (e - start) / 1000 + "s");
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(2);//设置保留几位小数
        double td = trueCount / pointList.size();
        double fd = falseCount / pointList.size();
        String format1 = format.format(td);
        String format2 = format.format(fd);
        System.out.println("匹配成功：" + trueCount + "  成功百分比：" + format1);
        System.out.println("匹配失败：" + falseCount + "  失败百分比：" + format2);
        System.out.println("未匹配成功经纬度：" + sb.toString());
    }

    @Test
    public void testSystemAlgori() {
        double trueCount = 0;
        double falseCount = 0;
        StringBuilder sb = new StringBuilder();
        System.out.print("使用目前系统算法进行匹配，总数" + pointList.size());
        long start = System.currentTimeMillis();
        for (Object[] point : pointList) {
            boolean flag = false;
            for (Object[] pl : shape) {
                String s = pl[0] + "";
                if (s.contains("object")) continue;
                if (SysApi.convertAndCheck(point[0] + "", point[1] + "", s)) {
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
        long e = System.currentTimeMillis();
        System.out.println("   耗时" + (e - start) / 1000 + "s");
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(2);//设置保留几位小数
        double td = trueCount / pointList.size();
        double fd = falseCount / pointList.size();
        String format1 = format.format(td);
        String format2 = format.format(fd);
        System.out.println("匹配成功：" + trueCount + "  成功百分比：" + format1);
        System.out.println("匹配失败：" + falseCount + "  失败百分比：" + format2);
        System.out.println("未匹配成功经纬度：" + sb.toString());
    }

    @Test
    public void testOne() {/*
        String lng = "114.15844737719041";
        String lat = "22.611404708799405";*/
        String lng = "114.15844737719";
        String lat = "22.6114047087994";
        boolean flag = false;
        for (Object[] pl : shape) {
            String s = pl[0] + "";
            if (s.contains("object")) continue;
            if (AlgoriUtil.isInPg(lng, lat, s)) {
                flag = true;
                System.out.println("匹配成功");
                break;
            }
        }
        if (!flag) {
            System.out.println("未匹配成功！");
        }
    }
}
