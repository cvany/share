package com.fpx.pds.algori;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ShapeJudge {

    public static void main(String[] args) {
        Point2D.Double point = new Point2D.Double(6, 8);
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
                } else if (Math.abs(target) > Math.abs(source)) {
                    count++;
                }
            }
            p1 = p2;
        }
        if (count % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*    *//**
     * 计算斜率是否相等
     *
     * @param p     当前的点
     * @param p1    第一个邻接点
     * @param p2    相邻第一个点的邻接点
     * @param count
     * @return
     *//*
    private static boolean calSlope(Point2D.Double p, Point2D.Double p1, Point2D.Double p2, int count) {
        double source = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        double target = (p1.getY() - p.getY()) / (p1.getX() - p.getX());
        if (target == source) {
            return true;
        } else if (target > source) {
            count++;
        }
        return false;
    }*/

}
