package com.jim.demo.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public final class Logging {

    static {
        URL resource = Logging.class.getClassLoader().getResource("logging.properties");
        PropertyConfigurator.configure(resource);
    }

    private static Logger console = Logger.getLogger("console");
    private static Logger javase = Logger.getLogger("javase");
    private static Logger mail = Logger.getLogger("mail");

    public static void debug(Object message) {
        console.debug(message);
    }

    public static void debug(Object message, Throwable t) {
        console.debug(message, t);
    }

    public static void info(Object message) {
        javase.info(message);
    }

    public static void info(Object message, Throwable t) {
        javase.info(message, t);
    }

    public static void error(Object message) {
        mail.error(message);
    }

    public static void error(Object message, Throwable t) {
        mail.error(message, t);
    }

    public static void warn(Object message) {
        mail.warn(message);
    }

    public static void warn(Object message, Throwable t) {
        mail.warn(message, t);
    }
}
