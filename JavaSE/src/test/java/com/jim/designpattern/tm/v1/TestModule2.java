package com.jim.designpattern.tm.v1;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestModule2 {
    @Test
    public void testGetResult() throws Exception {
        AbstractTemplate template = new Module2();
        template.getResult();
    }
}
