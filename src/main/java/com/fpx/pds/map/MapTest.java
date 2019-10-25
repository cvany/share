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
        String address = AddressHelper.prettify("中国 广东省 深圳市 宝安区 西乡街道~~~西乡鹤洲南片工业区2-3号阳光工业园C栋厂房B段第四层");
//        System.out.println(address);
        try {
            Map<String, String> json = MapUtil.getGeocoderLatitude("中国 广东省 深圳市 龙岗区 龙岗街道~~~龙东社区深汕路龙岗段292号银龙工业城A6栋4楼深圳市瑞之浩科技有限公司");
            System.out.println(json.get("lng") + "," + json.get("lat"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
