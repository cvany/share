
package com.fpx.pds.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 过滤特殊字符
 *
 * @Author: cuiwy
 * @Date: 2019/1/3 16:38
 * @version: v1.0.0
 */
public class StringFilter {

    public static void main(String[] args) {
        String str = "  [  0 34-)";
        String regEx = "[` 　~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）\\-——+|{}【】'；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String trim = m.replaceAll("").trim();
        System.out.println(trim);
    }
}
