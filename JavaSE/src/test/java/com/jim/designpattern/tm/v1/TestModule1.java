package com.jim.designpattern.tm.v1;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestModule1 {
    @Test
    public void testGetResult() throws Exception {
        AbstractTemplate template = new Module1();
        template.getResult();
    }
}
