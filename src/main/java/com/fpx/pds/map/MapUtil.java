package com.fpx.pds.map;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2019/7/2 10:10
 * @version: v1.0.0
 */
public class MapUtil {

    /**
     * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
     */
    public static Map<String, String> getGeocoderLatitude(String address) {
        if (StringUtils.isBlank(address)) {
            return null;
        }
        BufferedReader in = null;
        try {
            Map paramsMap = new LinkedHashMap<String, String>();
            paramsMap.put("address", address);
            paramsMap.put("output", "json");
            paramsMap.put("ak", "hSg8M54PGnV1tc5Gy13gnefEtmWsQ7yk");
            String quest = GetLatitude.toQueryString(paramsMap);
            URL tirc = new URL(
                    "http://api.map.baidu.com/geocoder/v2/?" + quest + "&sn=" + GetLatitude.result(paramsMap));

            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            System.out.println(str);
            Map<String, String> map = null;
            if (StringUtils.isNotEmpty(str)) {
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                int precise = str.lastIndexOf("precise");

                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    String pre = str.substring(precise + 9, precise + 10);
//                    if (Integer.parseInt(pre) == 1) {
                        map = new HashMap<String, String>();
                        map.put("lng", lng);
                        map.put("lat", lat);
                        return map;
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
