package com.jim;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 1/16/14
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMath {
    @Test
    public void testName() throws Exception {
        String str = "123456789";
        int[] size = new int[10];
        for (int i = 0; i < 10; i++) {

            int x = str.codePointCount(0, str.length());
            size[i] = x;
            System.out.println(x);
        }
        Arrays.sort(size);
        System.out.println(size[9]);
    }
}
