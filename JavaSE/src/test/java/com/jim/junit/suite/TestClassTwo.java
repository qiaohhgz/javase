package com.jim.junit.suite;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/31/13
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassTwo {

    @Test
    public void testA() throws Exception {
        //TODO A
        assert TestClassOne.num == 10;
        System.out.println("Testing A Method");
    }

    @Test
    public void testB() throws Exception {
        //TODO B
        assert TestClassOne.num == 10;
        System.out.println("Testing B Method");
    }
}
