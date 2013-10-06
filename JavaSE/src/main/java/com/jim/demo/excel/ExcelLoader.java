package com.jim.demo.excel;

import common.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 6/13/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExcelLoader extends XLSXAbstract {
    protected static final Logger log = Logger.getLogger(ExcelLoader.class);

    private int cellIndex;
    private String keyword;

    @Override
    public void optRows(int sheetIndex, int rowIndex, List<String> row) throws SQLException {
        if(row != null && row.size() > 0){

        }
    }

    public Object searchKeyword(int sheetIndex, String filePath, int cellIndex, String keyword) {
        try {
            this.cellIndex = cellIndex;
            this.keyword = keyword;
            processOneSheet(filePath, sheetIndex);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
}
