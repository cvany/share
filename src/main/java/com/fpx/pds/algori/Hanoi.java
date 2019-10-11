
package com.fpx.pds.algori;

/**
 * @Author: cuiwy
 * @Date: 2019/8/13 08:46
 * @version: v1.0.0
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(2);
    }

    private static void hanoi(int i) {
        if (i > 0) {
            func(i, "left", "mid", "right");
        }
    }

    private static void func(int i, String from, String mid, String to) {
        if (i == 1) {
            System.out.println("move from " + from + " to " + to);
        } else {
            func(i - 1, from, to, mid);
            func(1, from, mid, to);
            func(i - 1, mid, from, to);
        }
    }
}
