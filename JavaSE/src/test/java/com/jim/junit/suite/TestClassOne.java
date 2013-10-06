package com.jim.junit.suite;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/31/13
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassOne {
    static int num = 0;
    @Test
    public void testOne() throws Exception {
        //TODO One
        num = 10;
        System.out.println("Testing One Method");
    }

    @Test
    public void testTwo() throws Exception {
        //TODO Two
        assert num == 10;
        System.out.println("Testing Two Method");
    }
}
