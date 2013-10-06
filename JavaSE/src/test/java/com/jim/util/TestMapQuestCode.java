package com.jim.util;

import org.junit.Test;

import static com.jim.util.proxy.MyProxy.applyProxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/16/13
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMapQuestCode {

    @Test
    public void testIsSuccess() throws Exception {
        applyProxy();
        MapQuestCode mapQuestCode = new MapQuestCode("Edison NY");
        mapQuestCode.isSuccess();
    }
}
