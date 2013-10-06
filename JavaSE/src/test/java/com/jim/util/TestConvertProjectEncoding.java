package com.jim.util;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestConvertProjectEncoding {
    @Test
    public void testConvert() throws Exception {
        String path = "D:\\test\\a.txt";
        String[] filter = new String[]{"a.txt"};
        String sourceCharset = "ANSI";
        String targetCharset = "GBK";

        ConvertProjectEncoding projectEncoding = new ConvertProjectEncoding();
        projectEncoding.convert(path, sourceCharset, targetCharset, filter);
    }
}
