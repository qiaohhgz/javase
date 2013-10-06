package com.jim.designpattern.tm.v2;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMakeJeep {
    @Test
    public void testMake() throws Exception {
        MakeCar make = new MakeJeep();
        make.make();
    }
}
