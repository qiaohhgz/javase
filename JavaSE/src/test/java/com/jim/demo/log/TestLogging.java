package com.jim.demo.log;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestLogging {

    @Test(timeout = 1000)
    public void testDebug() throws Exception {
        Logging.debug("debug", null);
        Logging.debug("debug", new Exception());
    }

    @Test(timeout = 1000)
    public void testInfo() throws Exception {
        Logging.info("info");
        Logging.info("info", new Exception());
    }

    @Test(timeout = 1000)
    public void testWarn() throws Exception {
        Logging.warn("warn");
        Logging.warn("warn", new Exception());
    }

    @Test(timeout = 1000)
    public void testError() throws Exception {
        Logging.error("error");
        Logging.error("error", new Exception());
    }
}
