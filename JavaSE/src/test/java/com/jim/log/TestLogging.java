package com.jim.log;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/14/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestLogging {
    Logger moduleA = Logger.getLogger("A");
    Logger moduleB = Logger.getLogger("B");
    Logger moduleC = Logger.getLogger("C");
    Logger moduleError = Logger.getLogger("error");

    @Test
    public void testLog() throws Exception {
        Appender appender = new ConsoleAppender();
        assertNotNull(moduleA);
        assertNotNull(moduleB);
        assertNotNull(moduleC);
        assertNotNull(moduleError);

//        printList(moduleA);
//        printList(moduleB);
//        printList(moduleC);
        print(moduleError);
    }

    private void print(Logger log) {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
    }
}
