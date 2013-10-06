package com.jim.demo.excel;

import com.jim.util.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FileTest {

    String filename = "D:/IDESpringSourceTool/Workspaces/Ping/url.txt";
    String filenameExcel = "C:/Users/user/Desktop/AdvertID-DestinationURL-DisplayURL.xlsx";

    //		@Test
    public void test() throws IOException, InterruptedException {
        List<String> buffer = FileUtils.readFileToList(new File(filename), 0, 700000);
        System.out.println(buffer.size());
        Thread.sleep(100000);
    }

    //	@Test
    public void testAppacheReadFile() throws IOException, InterruptedException {
        List<String> list = org.apache.commons.io.FileUtils.readLines(new File(filename), "UTF-8");
        System.out.println(list.size());
        Thread.sleep(100000);
    }

    //	@Test
    public void testXssfReadExcel() {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(filenameExcel)));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //	@Test
    public void testFile() throws IOException, InterruptedException {
        int start = 0;
        int end = start + 100000;
        List<String> list = FileUtils.readFileToList(new File(filename), start, Integer.MAX_VALUE);
        System.out.println(list.size());
        Thread.sleep(100000);
    }

    //	@Test
    public void testGetFileLines() throws IOException {
        int lines = FileUtils.getFileLines(new File(filename));
        System.out.println(lines);
    }
}
