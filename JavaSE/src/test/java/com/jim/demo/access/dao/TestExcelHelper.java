package com.jim.demo.access.dao;

import common.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static com.jim.demo.access.dao.ExcelHelper.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/16/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestExcelHelper {
    public static final Logger log = Logger.getLogger(ExcelHelper.class);

    @Test
    public void testReadExcel() throws Exception {
        String fileName = "Chart-201211.xlsx";
        URL url = ExcelHelper.class.getClassLoader().getResource(fileName);
        log.debug("Excel file path:" + url.getPath());
        int year = 2012, month = 11;

        try {
            HSSFWorkbook workbook = new HSSFWorkbook(url.openStream());
            readExcel(workbook, year, month);
        } catch (OfficeXmlFileException e) {
            XSSFWorkbook workbook = new XSSFWorkbook(url.openStream());
            readExcel(workbook, year, month);
        }
    }

    @Test
    public void testReadExcelFile() throws Exception {
        try {
            String fileName = "Chart-201211.xlsx";
            URL url = ExcelHelper.class.getClassLoader().getResource(fileName);
            String file = url.getFile();
            System.out.println(file);
            Workbook book = Workbook.getWorkbook(new File(file));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            // 得到第一列第一行的单元格
            Cell cell1 = sheet.getCell(0, 0);
            String result = cell1.getContents();
            System.out.println(result);
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testExportToExcelWithStream() throws Exception {
        exportToExcelWithStream(System.out);
    }
}
