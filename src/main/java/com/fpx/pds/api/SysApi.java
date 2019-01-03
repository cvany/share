/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.api;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2019/1/3 14:25
 * @version: v1.0.0
 */
public class SysApi {

    public static void main(String[] args) {
        //这个点是在地图内的
        /*String lng = "113.9202230000";
        String lat = "22.5783100000";*/
        //这个点不在地图内
        String lng = "113.9226830000";
        String lat = "22.5797710000";
        String json = "[{\"lng\":113.838718,\"lat\":22.604701},{\"lng\":113.871488,\"lat\":22.617912},{\"lng\":113.880255,\"lat\":22.621248},{\"lng\":113.888448,\"lat\":22.619514},{\"lng\":113.893982,\"lat\":22.617646},{\"lng\":113.90742,\"lat\":22.613509},{\"lng\":113.917122,\"lat\":22.600163},{\"lng\":113.92438,\"lat\":22.586283},{\"lng\":113.919062,\"lat\":22.571599},{\"lng\":113.914176,\"lat\":22.558116},{\"lng\":113.907852,\"lat\":22.544765},{\"lng\":113.899659,\"lat\":22.531146},{\"lng\":113.891035,\"lat\":22.537555},{\"lng\":113.881693,\"lat\":22.543697},{\"lng\":113.864014,\"lat\":22.556114},{\"lng\":113.857259,\"lat\":22.567995},{\"lng\":113.850935,\"lat\":22.580276},{\"lng\":113.844611,\"lat\":22.591355}]";
        long s = System.currentTimeMillis();
        System.out.println(convertAndCheck(lng, lat, json));
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - s) + "ms");
    }

    /***
     * 根据当前经纬度匹配该块区域的司机
     *
     * @param lng        经度
     * @param lat        纬度
     * @param jsonString 片区经纬度信集合
     * @return 是否在此片区域内
     */
    public static boolean convertAndCheck(String lng, String lat, String jsonString) {
        //经度列表
        ArrayList<Double> lngList = null;
        //纬度列表
        ArrayList<Double> latList = null;
        List<PickupPoint> listPoint = JSON.parseArray(jsonString, PickupPoint.class);
        if (null != listPoint && listPoint.size() != 0) {
            lngList = new ArrayList<>();
            latList = new ArrayList<>();
            for (PickupPoint point : listPoint) {
                lngList.add(point.getLng());
                latList.add(point.getLat());
            }
        }

        Double px = Double.parseDouble(lng);
        Double py = Double.parseDouble(lat);
        if (lngList != null && lngList.size() > 0 && latList != null && latList.size() > 0) {
            Double lngs[] = lngList.toArray(new Double[lngList.size()]);
            Double lats[] = latList.toArray(new Double[latList.size()]);
            if (lngs != null && lats != null) {
                if (isAmongMaxAndMin(px, py, lngs, lats)) {
                    return pnpoly(lngList.size(), lngs, lats, px, py);
                }
            }
        }
        return false;
    }

    public static boolean isAmongMaxAndMin(Double lng, Double lat, Double lngs[], Double lats[]) {
        quickSort(lngs);
        quickSort(lats);
        return lngs[0] < lng && lng < lngs[lngs.length - 1] && lats[0] < lat && lat < lats[lats.length - 1];
    }

    public static void quickSort(Double a[]) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Double a[], int low, int hight) {
        int i, j;
        Double index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i];
        while (i < j) {
            while (i < j && a[j] >= index) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j];
            }
            while (i < j && a[i] < index) {
                i++;
            }
            if (i < j) {
                a[j--] = a[i];
            }
        }
        a[i] = index;
        sort(a, low, i - 1);
        sort(a, i + 1, hight);

    }

    private static boolean pnpoly(int size, Double[] lngList, Double[] latList, Double px, Double py) {
        int i, j;
        boolean c = false;
        for (i = 0, j = size - 1; i < size; j = i++) {
            if (((latList[i] > py) != (latList[j] > py)) &&
                    (px < (lngList[j] - lngList[i]) * (py - latList[i]) / (latList[j] - latList[i]) + lngList[i])) {
                c = !c;
            }
        }
        return c;
    }

}
