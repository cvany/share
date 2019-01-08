
package com.fpx.pds.api;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/1/3 14:25
 * @version: v1.0.0
 */
public class SysApi {

    public static void main(String[] args) {

        String lng ="113.69150073259357";
        String lat ="22.818952108500625";
        String json  ="[{\"lng\":113.668091,\"lat\":22.855914},{\"lng\":113.653143,\"lat\":22.848454},{\"lng\":113.634746,\"lat\":22.843659},{\"lng\":113.630088,\"lat\":22.835886},{\"lng\":113.637706,\"lat\":22.829357},{\"lng\":113.641411,\"lat\":22.820742},{\"lng\":113.648813,\"lat\":22.815945},{\"lng\":113.657289,\"lat\":22.813568},{\"lng\":113.664983,\"lat\":22.809549},{\"lng\":113.670445,\"lat\":22.809349},{\"lng\":113.676769,\"lat\":22.809082},{\"lng\":113.673535,\"lat\":22.804152},{\"lng\":113.678637,\"lat\":22.804418},{\"lng\":113.681871,\"lat\":22.805418},{\"lng\":113.683668,\"lat\":22.805085},{\"lng\":113.68532,\"lat\":22.804352},{\"lng\":113.683739,\"lat\":22.800953},{\"lng\":113.682374,\"lat\":22.798155},{\"lng\":113.68108,\"lat\":22.795156},{\"lng\":113.680506,\"lat\":22.792957},{\"lng\":113.681943,\"lat\":22.791025},{\"lng\":113.674613,\"lat\":22.784961},{\"lng\":113.673966,\"lat\":22.780363},{\"lng\":113.685392,\"lat\":22.786294},{\"lng\":113.69186,\"lat\":22.782862},{\"lng\":113.700484,\"lat\":22.782495},{\"lng\":113.707095,\"lat\":22.782162},{\"lng\":113.716114,\"lat\":22.782662},{\"lng\":113.723265,\"lat\":22.784028},{\"lng\":113.723445,\"lat\":22.785694},{\"lng\":113.721037,\"lat\":22.790159},{\"lng\":113.717192,\"lat\":22.792824},{\"lng\":113.71615,\"lat\":22.796223},{\"lng\":113.717408,\"lat\":22.800654},{\"lng\":113.714246,\"lat\":22.807816},{\"lng\":113.712234,\"lat\":22.814413},{\"lng\":113.719312,\"lat\":22.815212},{\"lng\":113.722295,\"lat\":22.816012},{\"lng\":113.728331,\"lat\":22.817311},{\"lng\":113.733613,\"lat\":22.819909},{\"lng\":113.768622,\"lat\":22.816627},{\"lng\":113.786014,\"lat\":22.815528},{\"lng\":113.803638,\"lat\":22.813063},{\"lng\":113.804321,\"lat\":22.811014},{\"lng\":113.804267,\"lat\":22.809748},{\"lng\":113.804195,\"lat\":22.809115},{\"lng\":113.804483,\"lat\":22.807933},{\"lng\":113.805884,\"lat\":22.807983},{\"lng\":113.807106,\"lat\":22.81278},{\"lng\":113.815065,\"lat\":22.820342},{\"lng\":113.813412,\"lat\":22.825338},{\"lng\":113.807016,\"lat\":22.825472},{\"lng\":113.806232,\"lat\":22.838146},{\"lng\":113.802927,\"lat\":22.838179},{\"lng\":113.799908,\"lat\":22.837646},{\"lng\":113.799273,\"lat\":22.814762},{\"lng\":113.768018,\"lat\":22.816711},{\"lng\":113.747961,\"lat\":22.819026},{\"lng\":113.737153,\"lat\":22.823373},{\"lng\":113.745669,\"lat\":22.822741},{\"lng\":113.748033,\"lat\":22.822257},{\"lng\":113.745826,\"lat\":22.82516},{\"lng\":113.750209,\"lat\":22.824411},{\"lng\":113.752204,\"lat\":22.824244},{\"lng\":113.752545,\"lat\":22.826293},{\"lng\":113.751683,\"lat\":22.827159},{\"lng\":113.749994,\"lat\":22.830123},{\"lng\":113.744712,\"lat\":22.832422},{\"lng\":113.746472,\"lat\":22.839949},{\"lng\":113.742915,\"lat\":22.842147},{\"lng\":113.740508,\"lat\":22.841881},{\"lng\":113.738208,\"lat\":22.842714},{\"lng\":113.734255,\"lat\":22.844945},{\"lng\":113.73307,\"lat\":22.847609},{\"lng\":113.731507,\"lat\":22.853621},{\"lng\":113.727662,\"lat\":22.853721},{\"lng\":113.726728,\"lat\":22.853821},{\"lng\":113.725829,\"lat\":22.854154},{\"lng\":113.725434,\"lat\":22.853854},{\"lng\":113.724931,\"lat\":22.853687},{\"lng\":113.724212,\"lat\":22.853021},{\"lng\":113.722595,\"lat\":22.852788},{\"lng\":113.720907,\"lat\":22.852988},{\"lng\":113.719326,\"lat\":22.853254},{\"lng\":113.717242,\"lat\":22.854287},{\"lng\":113.715517,\"lat\":22.856118},{\"lng\":113.715122,\"lat\":22.855919},{\"lng\":113.714798,\"lat\":22.855619},{\"lng\":113.71372,\"lat\":22.855619},{\"lng\":113.71275,\"lat\":22.855552},{\"lng\":113.71178,\"lat\":22.855419},{\"lng\":113.710846,\"lat\":22.855053},{\"lng\":113.709121,\"lat\":22.85472},{\"lng\":113.707396,\"lat\":22.854487},{\"lng\":113.704881,\"lat\":22.854387},{\"lng\":113.703035,\"lat\":22.855115},{\"lng\":113.700713,\"lat\":22.854786},{\"lng\":113.699239,\"lat\":22.855419},{\"lng\":113.697838,\"lat\":22.856352},{\"lng\":113.69579,\"lat\":22.856818},{\"lng\":113.694124,\"lat\":22.857146},{\"lng\":113.69283,\"lat\":22.857746},{\"lng\":113.69107,\"lat\":22.857746},{\"lng\":113.687584,\"lat\":22.85678},{\"lng\":113.685752,\"lat\":22.858445},{\"lng\":113.684458,\"lat\":22.859577},{\"lng\":113.683344,\"lat\":22.858944},{\"lng\":113.682087,\"lat\":22.856913},{\"lng\":113.678278,\"lat\":22.855881},{\"lng\":113.677105,\"lat\":22.856118},{\"lng\":113.675565,\"lat\":22.856647},{\"lng\":113.673553,\"lat\":22.856913},{\"lng\":113.671828,\"lat\":22.85638},{\"lng\":113.670853,\"lat\":22.856085}]";


        long s = System.currentTimeMillis();
        System.out.println(convertAndCheck(lng, lat, json));
        long e = System.currentTimeMillis();
        System.out.println("目前系统所用算法 耗时：" + (e - s) + "ms");
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
        /*if (lngList != null && lngList.size() > 0 && latList != null && latList.size() > 0) {
            Double lngs[] = lngList.toArray(new Double[lngList.size()]);
            Double lats[] = latList.toArray(new Double[latList.size()]);
            if (lngs != null && lats != null) {
                if (isAmongMaxAndMin(px, py, lngs, lats)) {
                    return pnpoly(lngList.size(), lngs, lats, px, py);
                }
            }
        }*/
//        if (CollectionUtils.isNotEmpty(lngList) && CollectionUtils.isNotEmpty(latList)) {
            if (isAmongMaxAndMin(px, py, lngList.toArray(new Double[0]), latList.toArray(new Double[0]))) {
                return pnpoly(lngList.size(), lngList.toArray(new Double[0]), latList.toArray(new Double[0]), px, py);
            }
//        }
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
