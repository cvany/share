package com.fpx.pds.utils;

/**
 * 地址Helper
 *
 * @Author: cuiwy
 * @Date: 2019/7/22 17:28
 * @version: v1.0.0
 */
public class AddressHelper {

    /**
     * 地址标准化
     *
     * @param sourceAddress
     * @return
     */
    public static String prettify(String sourceAddress) {
        return StringUtil.removeDuplicateReach3Above(StringUtil.specialCharFilter(sourceAddress));
    }
}
