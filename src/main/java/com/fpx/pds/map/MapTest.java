package com.fpx.pds.map;

import com.fpx.pds.utils.AddressHelper;

import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2019/7/3 17:01
 * @version: v1.0.0
 */
public class MapTest {

    public static void main(String args[]) {
        String address = AddressHelper.prettify("中国 广东省 深圳市 宝安区 石岩街道~~~广东省 深圳市 宝安区 石岩街道石龙社区工业二路一号惠科平板显示产业园7栋负一楼");
        System.out.println(address);
        try {
            Map<String, String> json = MapUtil.getGeocoderLatitude(address);
            System.out.println(json.get("lng") + "," + json.get("lat"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
