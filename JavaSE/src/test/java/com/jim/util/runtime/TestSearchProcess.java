package com.jim.util.runtime;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/29/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestSearchProcess {
    @Test
    @Ignore
    public void testRun() throws Exception {
        new SearchProcess().getPIDByPort(8080);
    }
}
