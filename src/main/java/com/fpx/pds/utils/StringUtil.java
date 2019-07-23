package com.fpx.pds.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串工具
 *
 * @Author: cuiwy
 * @Date: 2019/7/19 14:35
 * @version: v1.0.0
 */
public class StringUtil {

    public static void main(String[] args) {
        String str = "中国 广东省 东莞市 塘厦镇 ~~~广东省 东莞市 塘厦镇 广东省东莞市塘厦镇清湖头高丽工业区桥蛟西路91号";
//        String str = "eabcabcfhdabcfhd";
        long strart = System.currentTimeMillis();
        String source = specialCharFilter(str);
        System.out.println(source);
        String duplicate = removeDuplicateReach3Above(source);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - strart) + "ms");
        System.out.println(duplicate);
    }

    /**
     * 移除重复字符（达到连续3个以上）
     *
     * @param str
     * @return
     */
    public static String removeDuplicateReach3Above(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        List<Integer> duplicateIndex = getDuplicateIndex(str);
        if (!CollectionUtils.isEmpty(duplicateIndex)) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0, len = duplicateIndex.size(); i < len; i += 2) {
                String temp = str.substring(duplicateIndex.get(i), duplicateIndex.get(i + 1));
                builder.append(temp);
            }
            return builder.toString();
        }

        return str;
    }

    private static List<Integer> getDuplicateIndex(String str) {
        char[] charArray = str.toCharArray();
        List<Integer> indexs = new ArrayList<>();
        //用于开始切割字符串
        indexs.add(0);
        //连续字符匹配的次数
        int matchCount = 0;
        for (int next = 1; next < charArray.length; next++) {
            //记录第一次匹配的index
            boolean firstMatchIndex = true;

            for (int twice = 0;
                 twice < charArray.length & next < charArray.length & twice < next;
                 twice++) {
                //连续匹配的标识
                boolean continuousMatchFlag = true;

                if (charArray[next] == charArray[twice]) {
                    continuousMatchFlag = false;
                    //记录首次匹配的index
                    if (firstMatchIndex) {
                        firstMatchIndex = false;
                        indexs.add(next);
                    }
                    matchCount++;
                    //继续向后滑动
                    next++;
                }
                //发现不连续则进行
                if (continuousMatchFlag && matchCount > 0) {
                    if (matchCount > 2) {
                        indexs.add(next);
                    } else {
                        //移除最后一个元素
                        indexs.remove(indexs.size() - 1);
                    }
                    next--;
                    matchCount = 0;
                    break;
                }
            }
        }
        if (matchCount > 0 && matchCount < 3) {
            //移除最后一个元素
            indexs.remove(indexs.size() - 1);
        } else if (matchCount >= 3) {
            indexs.add(str.length());
        }
        //用于切割字符串
        indexs.add(str.length());
        return indexs;
    }

    /**
     * 判断是否英文句子
     *
     * @param sentence
     * @return
     */
    public static boolean isEnglishSentence(String sentence) {
        if (StringUtils.isBlank(sentence)) {
            return false;
        }
        return sentence.matches("((\\w+\\s*[,.?!]?)|(\\w+'\\w+\\s*))*");
    }

    /**
     * 过滤重复字符
     *
     * @param word
     * @return
     */
    public static String repeatWordsFilter(String word) {
        if (StringUtils.isBlank(word)) {
            return word;
        }
        return word.replaceAll("(?s)(.)(?=.*\\1)", "");
    }

    /**
     * 过滤特殊字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String specialCharFilter(String str) throws PatternSyntaxException {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        String regEx = "[` 　~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）\\-——+|{}【】'；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
