package com.jim;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 2/14/14
 * Time: 10:22 AM
 * Copyrights: hibu, 2008 - 2014. All rights reserved.
 */
public class TestGetFileEncoding {
    @Test
    public void testName() throws Exception {
//        InputStreamReader reader = new InputStreamReader(new FileInputStream(new File("D:\\JimQiao\\1\\InternationalizationHelper.java")));
        InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("InternationalizationHelper.java.bak"));
        System.out.println(reader.getEncoding());
        for (int i = 8360; i < 9000; i++) {
            Thread.sleep(500);
            System.out.println(i + " " + (char) i);
        }
    }
}
