package com.jim.demo.randomAccessFile;

import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/4/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMultiThreadWriteDemo {
    @Test
    public void testWrite() throws Exception {
        MultiThreadWriteDemo demo = new MultiThreadWriteDemo();
        File file = new File("D:/test.txt");
        demo.write(file, 5);
    }
}
