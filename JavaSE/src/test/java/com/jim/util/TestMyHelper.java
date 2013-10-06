package com.jim.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class TestMyHelper {

    public static int sum = 0;
    public static int num = 0;

    //	@Test
    public void testConvertFileEncoding() {
        String path = "F:/IDEMyEclipse10/Workspaces/TankWar/TankDanJiWars/TankWar";
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.isFile()) {
                    if (file.getName().endsWith(".java") || file.getName().endsWith(".txt")) {
                        return true;
                    }
                }
                return false;
            }
        };

        for (int i = 0; i <= 6; i++) {
            convert(new File(path + "2." + i), filter);
        }
        System.out.println("总共转换" + sum + "个文件，成功转换" + num + "个文件");
    }

    private void convert(File file, FileFilter filter) {
        File[] files = file.listFiles(filter);
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                convert(files[i], filter);
            } else {
                try {
                    sum++;
                    boolean b = MyHelper.convertFileEncoding(files[i], "gb2312", "UTF-8");
                    if (b) {
                        num++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Logger log = Logger.getLogger(TestMyHelper.class);

    @Test
    public void testGetResource() {
        assertNotNull(MyHelper.getResource("log4j.properties"));
    }

}
