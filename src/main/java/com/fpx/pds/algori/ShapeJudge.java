
package com.fpx.pds.algori;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/1/7 11:07
 * @version: v1.0.0
 */
public class ShapeJudge {

    public static void main(String[] args) {
        Point2D.Double point = new Point2D.Double(6, 7);
        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        pointList.add(new Point2D.Double(4, 6));
        pointList.add(new Point2D.Double(7, 2));
        pointList.add(new Point2D.Double(10, 10));
        long s = System.currentTimeMillis();
        System.out.println(isIn(point, pointList));
        long e = System.currentTimeMillis();
        System.out.println("算法耗时：" + (e - s) + "ms");
    }

    public static boolean isIn(Point2D.Double point, List<Point2D.Double> list) {
        int size = list.size();//点的总数
        Point2D.Double p = point; //当前点
        Point2D.Double p1, p2; //两个邻接点
        int count = 0;//若目标斜率大，则自增1

        p1 = list.get(0);   //获取第一点
        for (int i = 1; i <= list.size(); i++) {
            if (p.equals(p1)) {
                return true;
            }
            p2 = list.get(i % size); //获取邻接点
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
                p1 = p2;
                continue;
            }
            //当前点x在两个邻接点之间
            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
                //若斜率相等则在同一条线上
                double source = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
                double target = (p1.getY() - p.getY()) / (p1.getX() - p.getX());
                if (target == source) {
                    return true;
                }
                //若两者斜率积为负，则不处理
                else if (target * source < 0) {
                    continue;
                } else if (target > source) {
                    count++;
                }
                System.out.println("目标：" + target);
                System.out.println("源：" + source);
                System.out.println(count);
            }
            p1 = p2;
        }
        //偶数表示在内
        if (count == 0) {
            return false;
        } else if (count % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    }

}
