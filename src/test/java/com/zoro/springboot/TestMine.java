package com.zoro.springboot;

/**
 * @author zoro
 * @date: 2018/12/6 10:59
 * @description:
 */
public class TestMine {

    private static final String SIMBLE = "\\^";
    public static void main(String[] args) {
        String a = "1^2";
        String[] b = a.split(SIMBLE);
        System.out.println(b[0]);
        System.out.println(b[1]);
    }
}
