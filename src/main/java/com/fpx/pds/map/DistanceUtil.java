package com.fpx.pds.map;


/**
 * @Author: cuiwy
 * @Date: 2019/6/27 11:25
 * @version: v1.0.0
 */
public class DistanceUtil {

    public static void main(String[] args) {
        //根据两点间的经纬度计算距离，单位：km
//        double s = getDistance(114.060312, 22.528494, 114.071451, 22.528828);
//        double s = getDistance(98.00624, 24.42639, 98.0061, 24.42635);
        double s1 = getDistance(114.02843663136794,22.66856635231803, 114.052801,22.646789);
        double s2 = getDistance(113.876764,22.577428, 113.880044,22.578185);
        System.out.println(s1);
        System.out.println(s2);
    }

    private static double rad(double d) {
        return d * Math.PI / 180.00; //角度转换成弧度
    }

    /*
     * 根据经纬度计算两点之间的距离（单位米）
     * */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double Lat1 = rad(latitude1); // 纬度
        double Lat2 = rad(latitude2);

        double a = Lat1 - Lat2;//两点纬度之差
        double b = rad(longitude1) - rad(longitude2); //经度之差
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

        s = s * 6378137.0;//弧长乘地球半径（半径为米）
        s = Math.round(s * 10000d) / 10000d;//精确距离的数值
        s = s / 1000;//将单位转换为km，如果想得到以米为单位的数据 就不用除以1000
        return s;
        //四舍五入 保留3位小数
//        DecimalFormat df = new DecimalFormat("0.000");
//        return df.format(s);
    }
}
