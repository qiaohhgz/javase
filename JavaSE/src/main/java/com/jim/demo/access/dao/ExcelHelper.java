package com.jim.demo.access.dao;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

public class ExcelHelper {

    private final static Logger log = Logger.getLogger(ExcelHelper.class);

    public static void readExcel(HSSFWorkbook workbook, int year, int month) {
        log.info("Excel version is 2003");
    }

    public static void readExcel(XSSFWorkbook workbook, int year, int month) {
        log.info("Excel version is 2007");
        int beginRow = 5;
        int beginCell = 1;
        int rowIndex = beginRow;
        int cellIndex = beginCell;
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<Work> works = new ArrayList<Work>();
        Date day, beginDate, endDate;
        double hours = 0;
        XSSFRow row;
        XSSFCell cell;
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        for (int i = 0; i < 31; i++) {
            cellIndex = beginCell;
            log.debug("rowIndex:" + rowIndex);

            row = sheet.getRow(rowIndex++);

            day = new Date();
            log.debug("day:" + day);

            cell = row.getCell(cellIndex++);
            beginDate = cell.getDateCellValue();
            log.debug("beginDate:" + beginDate);

            cell = row.getCell(cellIndex++);
            endDate = cell.getDateCellValue();
            log.debug("endDate:" + endDate);

            cell = row.getCell(cellIndex++);
            hours = cell.getNumericCellValue();
            log.debug("hours:" + hours);

            works.add(new Work(day, beginDate, endDate, hours));
        }
    }

    // / <summary>
    // / 导出Excel(以流的方式)
    // / </summary>
    public static void exportToExcelWithStream(OutputStream os) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(os);
        try {
            // 写入列标题
            osw.write("Name\tPassword\n");

            // 写入列内容
            for (int j = 0; j < 100; j++) {
                osw.write("jim" + j + "\t123\n");
            }
            osw.close();
        } catch (Exception e) {
            osw.close();
        }
    }
}
