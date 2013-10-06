package com.jim.download.ibm;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/9/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utility {
    public static void sleep(int nSecond) {
        try {
            Thread.sleep(nSecond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String sMsg) {
        System.err.println(sMsg);
    }

    public static void log(int sMsg) {
        System.err.println(sMsg);
    }
}
