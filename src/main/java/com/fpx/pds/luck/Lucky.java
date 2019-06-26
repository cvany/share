
package com.fpx.pds.luck;

import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;

/**
 * @Author: cuiwy
 * @Date: 2018/9/29 13:57
 * @version: v1.0.0
 */
public class Lucky {

    /**
     * 启动
     */
    @Test
    public void luck() {
        List<Map<String, List>> list = enter(1, "");
        System.out.println(JSON.toJSONString(list));
        System.out.println();
    }

    /**
     * 程序入口
     *
     * @param note  注数
     * @param color 彩种
     */
    public List<Map<String, List>> enter(int note, String color) {
        switch (color) {
            case Constant.WELFARECOLOR:
                return pacakgeWFData(note);
            case Constant.MARKSIX:
                return pacakgeMSData(note);

        }
        return pacakgeTWFData(note);
    }

    /**
     * 封装福利彩数据
     *
     * @param note
     * @return
     */
    public List<Map<String, List>> pacakgeWFData(int note) {
        List<Map<String, List>> list = new ArrayList();
        for (int i = 0; i < note; i++) {
            list.add(generateOneWelfareColor());
        }
        return list;
    }

    /**
     * 封装六合彩数据
     *
     * @param note
     * @return
     */
    public List<Map<String, List>> pacakgeMSData(int note) {
        List<Map<String, List>> list = new ArrayList();
        for (int i = 0; i < note; i++) {
            list.add(generateOneMarkSix());
        }
        return list;
    }

    /**
     * 封装三中三和四中四数据
     *
     * @param note
     * @return
     */
    public List<Map<String, List>> pacakgeTWFData(int note) {
        List<Map<String, List>> list = new ArrayList();
        for (int i = 0; i < note; i++) {
            list.add(generateOneThreeWithFour());
        }
        return list;
    }


    /**
     * 生成一注福利彩
     *
     * @return
     */
    private Map<String, List> generateOneWelfareColor() {
        Map<String, List> map = new HashMap<>();
        map.put(Constant.RED, generateColor(Constant.SIX, Constant.THIRTYANDTHREE));
        map.put(Constant.BLUE, generateColor(Constant.ONE, Constant.SIXTEEN));
        return map;
    }

    /**
     * 生成一注六合彩
     *
     * @return
     */
    private Map<String, List> generateOneMarkSix() {
        Map<String, List> map = new HashMap<>();
        map.put(Constant.FLATCODE, generateColor(Constant.SIX, Constant.FORTYANDNIGHT));
        map.put(Constant.SPECIALCODE, generateColor(Constant.ONE, Constant.FORTYANDNIGHT));
        return map;
    }

    /**
     * 生成一注三中三
     * 生成一注四中四（在三中三的基础上）
     *
     * @return
     */
    private Map<String, List> generateOneThreeWithFour() {
        Map<String, List> map = new HashMap<>();
        List<Integer> FList = generateColor(Constant.FOUR, Constant.FORTYANDNIGHT);
        map.put(Constant.F_Z_F, FList);
        List<Integer> cpList = Lists.newArrayList();
        cpList.addAll(FList);
        Random random = new Random();
        int i = random.nextInt(4);
        cpList.remove(i);
        map.put(Constant.T_Z_T, cpList);
        return map;
    }

    /**
     * 生成球
     *
     * @param num  个数
     * @param size 容器大小
     * @return
     */
    private List<Integer> generateColor(int num, int size) {
        List<Integer> result = new ArrayList<>();
        List<Integer> prepareData = prepareData(size);
        for (int i = 0; i < num; i++) {
            int index = generateIndex(prepareData.size());
            result.add(prepareData.get(index));
            prepareData.remove(index);
        }
        Collections.sort(result);
        return result;
    }

    /**
     * 生成幸运角标
     *
     * @param size
     * @return
     */
    private int generateIndex(int size) {
        return new Random().nextInt(size);
    }

    /**
     * 准备数据
     *
     * @param size
     * @return
     */
    private List<Integer> prepareData(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 常量
     */
    interface Constant {
        String RED = "red";
        String BLUE = "blue";
        String SPECIALCODE = "special_code";
        String FLATCODE = "flat_code";
        String MARKSIX = "MARKSIX";
        String T_Z_T = "T_Z_T";
        String F_Z_F = "F_Z_F";
        String WELFARECOLOR = "WELFARECOLOR";

        Integer ONE = 1;
        Integer FOUR = 4;
        Integer SIX = 6;
        Integer SIXTEEN = 16;
        Integer THIRTYANDTHREE = 33;
        Integer FORTYANDNIGHT = 49;

    }
}
