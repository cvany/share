
package com.fpx.pds.utils;

/**
 * @Author: cuiwy
 * @Date: 2018/12/13 15:36
 * @version: v1.0.0
 */
public class TimeUtil {

    /**
     * 根据毫秒返回具体时分秒
     *
     * @param millis
     * @return
     */
    public static String comsumeTime(long millis) {
        long ss = millis / 1000;
        StringBuilder sb = new StringBuilder();
        if (ss < 60) {
            sb.append(ss + " 秒 ");
        } else if (ss / 60 < 60) {
            sb.append(ss / 60 + " 分 " + (ss - (ss / 60) * 60) + " 秒 ");
        } else if (ss / 3600 < 24) {
            sb.append(ss / 3600 + " 时 " + (ss - (ss / 3600) * 3600) / 60 + " 分 " +
                    ((ss - (ss / 3600) * 3600) - (((ss - (ss / 3600) * 3600) / 60) * 60)) + " 秒");
        } else if (ss / 3600 / 24 < 356) {
            sb.append(ss / 3600 / 24 + " 天 " + (ss - (ss / 3600 / 24) * 24 * 3600) / 3600 + " 时 " +
                    ((ss - (((ss - (ss / 3600 / 24) * 24 * 3600) / 3600) * 3600) - ((ss / 24 / 3600) * 24 * 3600)) / 60) + " 分 " +
                    (ss - (((ss - (((ss - (ss / 3600 / 24) * 24 * 3600) / 3600) * 3600) - ((ss / 24 / 3600) * 24 * 3600)) / 60) * 60) -
                            (((ss - (ss / 3600 / 24) * 24 * 3600) / 3600) * 3600) - ((ss / 24 / 3600) * 24 * 3600)) + " 秒");
        }
        return sb.toString();
    }
}
