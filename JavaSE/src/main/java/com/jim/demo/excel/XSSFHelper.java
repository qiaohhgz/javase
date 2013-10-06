package com.jim.demo.excel;

import common.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 6/13/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class XSSFHelper {
    protected static final Logger log = Logger.getLogger(XSSFHelper.class);

    public void load(String filePath) {
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = null;
        try {
            FileInputStream in = new FileInputStream(new File((filePath)));
            xwb = new XSSFWorkbook(in);
        } catch (IOException e) {
            log.error("读取文件出错");
        }
        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        // 定义 row、cell
        XSSFRow row;
        // 循环输出表格中的内容
        StringBuilder rowValues = new StringBuilder();
        for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                // 通过 row.getCell(j).toString() 获取单元格内容，
                XSSFCell cell = row.getCell(j);
                if (cell != null) {
                    String value = cell.toString();
                    rowValues.append(value + "\t");
                }
            }
            log.debug(rowValues.toString());
            rowValues.delete(0, rowValues.length());
        }
    }

    public List<Object[]> searchKeyword(String keyword) {
        return null;
    }

}
