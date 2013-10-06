package com.jim.demo.randomAccessFile;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/4/13
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestRandomAccessFileDemo {
    private RandomAccessFileDemo demo;
    private File file;

    @Before
    public void init() throws Exception {
        demo = new RandomAccessFileDemo();
        file = File.createTempFile("test.txt", ".temp");
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void testWriteAndRead() throws Exception {
        demo.write(file);
        String[] actual = demo.read(file).toArray(new String[3]);
        String[] expected = new String[]{
                "第二个人的信息是: 姓名-->lisi    ,年龄-->32",
                "第一个人的信息是: 姓名-->ZhangSan,年龄-->30",
                "第三个人的信息是: 姓名-->wangwu  ,年龄-->36"};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSeek() throws Exception {
        File file = new File("D:/test.txt");
        if(file.exists()){
            file.delete();
        }
        demo.seek(file);
        String actual = demo.readSkipString(file);
        assertEquals(actual, "HelloWorld");
    }
}
