/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.api;

import com.alibaba.fastjson.JSON;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2019/1/3 13:42
 * @version: v1.0.0
 */
public class BDApiCopy {

    public static void main(String[] args) {
        //这个点是在地图内的（宝安区海宝餐厅）
        String lng = "113.9202450000";
        String lat = "22.5786470000";
        //这个点不在地图内
        /*String lng = "113.9226830000";
        String lat = "22.5797710000";*/
        String json = "[{\"lng\":113.838718,\"lat\":22.604701},{\"lng\":113.871488,\"lat\":22.617912},{\"lng\":113.880255,\"lat\":22.621248},{\"lng\":113.888448,\"lat\":22.619514},{\"lng\":113.893982,\"lat\":22.617646},{\"lng\":113.90742,\"lat\":22.613509},{\"lng\":113.917122,\"lat\":22.600163},{\"lng\":113.92438,\"lat\":22.586283},{\"lng\":113.919062,\"lat\":22.571599},{\"lng\":113.914176,\"lat\":22.558116},{\"lng\":113.907852,\"lat\":22.544765},{\"lng\":113.899659,\"lat\":22.531146},{\"lng\":113.891035,\"lat\":22.537555},{\"lng\":113.881693,\"lat\":22.543697},{\"lng\":113.864014,\"lat\":22.556114},{\"lng\":113.857259,\"lat\":22.567995},{\"lng\":113.850935,\"lat\":22.580276},{\"lng\":113.844611,\"lat\":22.591355}]";
        long s = System.currentTimeMillis();
        System.out.println(isInPolygon(lng, lat, json));
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - s) + "ms");
    }

    /**
     * 判断当前位置是否在多边形区域内
     *
     * @param lng  经度
     * @param lat  纬度
     * @param json
     * @return
     */
    public static boolean isInPolygon(String lng, String lat, String json) {
        double p_x = Double.parseDouble(lng);
        double p_y = Double.parseDouble(lat);
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point2D.Double(p_x, p_y);

        List<Map> listPoint = JSON.parseArray(json, Map.class);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        for (Map map : listPoint) {
            Point2D.Double polygonPoint = new Point2D.Double(Double.parseDouble(map.get("lng") + ""), Double.parseDouble(map.get("lat") + ""));
            pointList.add(polygonPoint);
        }
        return IsPtInPoly(point, pointList);
    }

    /**
     * 判断点是否在多边形内，如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
     *
     * @param point 检测点
     * @param pts   多边形的顶点
     * @return 点在多边形内返回true, 否则返回false
     */
    public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts) {
        int N = pts.size();
        boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        int intersectCount = 0;//cross points count of x
        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        Point2D.Double p1, p2;//neighbour bound vertices
        Point2D.Double p = point; //当前点

        p1 = pts.get(0);//left vertex
        for (int i = 1; i <= N; ++i) {//check all rays
            if (p.equals(p1)) {
                return boundOrVertex;//p is an vertex
            }

            p2 = pts.get(i % N);//right vertex
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {//ray is outside of our interests
                p1 = p2;
                continue;//next ray left point
            }

            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {//ray is crossing over by the algorithm (common part of)
                if (p.y <= Math.max(p1.y, p2.y)) {//x is before of ray
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {//overlies on a horizontal ray
                        return boundOrVertex;
                    }

                    if (p1.y == p2.y) {//ray is vertical
                        if (p1.y == p.y) {//overlies on a vertical ray
                            return boundOrVertex;
                        } else {//before ray
                            ++intersectCount;
                        }
                    } else {//cross point on the left side
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;//cross point of y
                        if (Math.abs(p.y - xinters) < precision) {//overlies on a ray
                            return boundOrVertex;
                        }

                        if (p.y < xinters) {//before ray
                            ++intersectCount;
                        }
                    }
                }
            } else {//special case when ray is crossing through the vertex
                if (p.x == p2.x && p.y <= p2.y) {//p crossing over p2
                    Point2D.Double p3 = pts.get((i + 1) % N); //next vertex
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {//p.x lies between p1.x & p3.x
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;
        }
        if (intersectCount % 2 == 0) {//偶数在多边形外
            return false;
        } else { //奇数在多边形内
            return true;
        }
    }

}
