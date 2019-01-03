/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.api;

import com.alibaba.fastjson.JSON;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/1/3 11:21
 * @version: v1.0.0
 */
public class BaiduApi {


    public static void main(String[] args) {
        //经度列表
        ArrayList<Double> lngList = null;
        //纬度列表
        ArrayList<Double> latList = null;
        String jsonString = "[{\"lng\":113.838718,\"lat\":22.604701},{\"lng\":113.871488,\"lat\":22.617912},{\"lng\":113.880255,\"lat\":22.621248},{\"lng\":113.888448,\"lat\":22.619514},{\"lng\":113.893982,\"lat\":22.617646},{\"lng\":113.90742,\"lat\":22.613509},{\"lng\":113.917122,\"lat\":22.600163},{\"lng\":113.92438,\"lat\":22.586283},{\"lng\":113.919062,\"lat\":22.571599},{\"lng\":113.914176,\"lat\":22.558116},{\"lng\":113.907852,\"lat\":22.544765},{\"lng\":113.899659,\"lat\":22.531146},{\"lng\":113.891035,\"lat\":22.537555},{\"lng\":113.881693,\"lat\":22.543697},{\"lng\":113.864014,\"lat\":22.556114},{\"lng\":113.857259,\"lat\":22.567995},{\"lng\":113.850935,\"lat\":22.580276},{\"lng\":113.844611,\"lat\":22.591355}]";
        List<PickupPoint> listPoint = JSON.parseArray(jsonString, PickupPoint.class);
        if (null != listPoint && listPoint.size() != 0) {
            lngList = new ArrayList<>();
            latList = new ArrayList<>();
            for (PickupPoint point : listPoint) {
                lngList.add(point.getLng());
                latList.add(point.getLat());
            }
        }
        Double[] ln = lngList.toArray(new Double[lngList.size()]);
        Double[] la = latList.toArray(new Double[latList.size()]);
        Double px = 22.5774280000;
        Double py = 113.8767640000;

        System.out.println(isInPolygon(px, py, ln, la));
    }

    /**
     * 判断是否在多边形区域内
     *
     * @param pointLon 要判断的点的纵坐标
     * @param pointLat 要判断的点的横坐标
     * @param lon      区域各顶点的纵坐标数组
     * @param lat      区域各顶点的横坐标数组
     * @return
     */
    public static boolean isInPolygon(Double pointLon, Double pointLat, Double[] lon,
                                      Double[] lat) {
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point2D.Double(pointLon, pointLat);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        double polygonPoint_x = 0.0, polygonPoint_y = 0.0;
        for (int i = 0; i < lon.length; i++) {
            polygonPoint_x = lon[i];
            polygonPoint_y = lat[i];
            Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
            pointList.add(polygonPoint);
        }
        return check(point, pointList);
    }

    /**
     * 一个点是否在多边形内
     *
     * @param point   要判断的点的横纵坐标
     * @param polygon 组成的顶点坐标集合
     * @return
     */
    private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath peneralPath = new java.awt.geom.GeneralPath();

        Point2D.Double first = polygon.get(0);
        // 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
        peneralPath.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            // 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
            peneralPath.lineTo(d.x, d.y);
        }
        // 将几何多边形封闭
        peneralPath.lineTo(first.x, first.y);
        peneralPath.closePath();
        // 测试指定的 Point2D 是否在 Shape 的边界内。
        return peneralPath.contains(point);
    }


}
