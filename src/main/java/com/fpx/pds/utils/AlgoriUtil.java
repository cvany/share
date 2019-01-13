
package com.fpx.pds.utils;

import com.alibaba.fastjson.JSON;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 算法工具
 *
 * @Author: cuiwy
 * @Date: 2019/1/7 11:12
 * @version: v1.0.0
 */
public class AlgoriUtil {


    /**
     * 判断当前位置是否在多边形区域内
     *
     * @param lng  经度
     * @param lat  纬度
     * @param json
     * @return
     */
    public static boolean isInPg(String lng, String lat, String json) {
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point2D.Double(Double.parseDouble(lng), Double.parseDouble(lat));
        List<Map> listPoint = JSON.parseArray(json, Map.class);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        for (Map map : listPoint) {
            Point2D.Double polygonPoint = new Point2D.Double(Double.parseDouble(map.get("lng") + ""), Double.parseDouble(map.get("lat") + ""));
            pointList.add(polygonPoint);
        }
        return AlgoriUtil.isIn(point, pointList, true);
    }


    /**
     * 判断点是否在多边形内
     *
     * @param point         当前点
     * @param pointList     多边形点集合
     * @param boundOrVertex 若当前点落边上或顶点上，需要返回true，则传入true；反之，传入false
     * @return
     */
    public static boolean isIn(Point2D.Double point, List<Point2D.Double> pointList, boolean boundOrVertex) {
        int size = pointList.size();
        int intersectCount = 0;
        //浮点类型计算时候与0比较时候的容差
        double precision = 2e-10;
        Point2D.Double p1, p2;
        Point2D.Double p = point;
        //获取第一个点
        p1 = pointList.get(0);
        for (int i = 1; i <= size; ++i) {
            if (p.equals(p1)) {
                return boundOrVertex;
            }
            p2 = pointList.get(i % size);
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
                p1 = p2;
                continue;
            }
            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
                if (p.y <= Math.max(p1.y, p2.y)) {
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {
                        return boundOrVertex;
                    }
                    if (p1.y == p2.y) {
                        if (p1.y == p.y) {
                            return boundOrVertex;
                        } else {
                            ++intersectCount;
                        }
                    } else {
                        //计算斜率
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
                        if (Math.abs(p.y - xinters) < precision) {
                            return boundOrVertex;
                        }
                        if (p.y < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            } else {
                if (p.x == p2.x && p.y <= p2.y) {
                    Point2D.Double p3 = pointList.get((i + 1) % size);
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;
        }
        //若交叉的点为偶数，则在多边形外
        if (intersectCount % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }
}
