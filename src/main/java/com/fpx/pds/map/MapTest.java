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
        String address = AddressHelper.prettify("中国 广东省深圳市 龙岗区 坂田街道~~~中兴路与环城东路交叉口东180米 深圳鑫拓供应链有限公司");
        System.out.println(address);
        try {
            Map<String, String> json = MapUtil.getGeocoderLatitude(address);
            System.out.println(json.get("lng") + "," + json.get("lat"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
