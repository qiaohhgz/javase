package com.jim.util;

import org.junit.Test;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/16/13
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMyHotKeyHelper {
    @Test
    public void testAdd() throws Exception {
        MyHotKeyHelper globalEventKey = new MyHotKeyHelper();
        globalEventKey.add(KeyEvent.VK_A, false, false, false, new Runnable() {
            public void run() {
                System.out.println("test");
            }
        });
    }
}
