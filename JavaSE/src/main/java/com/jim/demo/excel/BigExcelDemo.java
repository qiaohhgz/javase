package com.jim.demo.excel;

import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Assert;

import java.io.FileOutputStream;
import java.io.IOException;

public class BigExcelDemo {

	/**
	 * manually control how rows are flushed to disk
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void writeBigExcel(String fileName) throws IOException {
		SXSSFWorkbook wb = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
		wb.setCompressTempFiles(true); // temp files will be gzipped
		Sheet sh = wb.createSheet();
		for (int rownum = 0; rownum < 1000; rownum++) {
			Row row = sh.createRow(rownum);
			for (int cellnum = 0; cellnum < 10; cellnum++) {
				Cell cell = row.createCell(cellnum);
				String address = new CellReference(cell).formatAsString();
				cell.setCellValue(address);
			}

			// manually control how rows are flushed to disk 
			if (rownum % 100 == 0) {
				((SXSSFSheet) sh).flushRows(100); // retain 100 last rows and flush all others

				// ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
				// this method flushes all rows
			}

		}

		FileOutputStream out = new FileOutputStream(fileName);
		wb.write(out);
		out.close();

		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}

	/**
	 * keep 100 rows in memory, exceeding rows will be flushed to disk
	 * 
	 * @param fileName
	 * @throws IOException
	 * @throws BiffException
	 */
	public void writeBigExcelAutoFlush(String fileName) throws IOException, BiffException {
		SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
		wb.setCompressTempFiles(true); // temp files will be gzipped
		Sheet sh = wb.createSheet();
		for (int rownum = 0; rownum < 1000; rownum++) {
			Row row = sh.createRow(rownum);
			for (int cellnum = 0; cellnum < 10; cellnum++) {
				Cell cell = row.createCell(cellnum);
				String address = new CellReference(cell).formatAsString();
				cell.setCellValue(address);
			}

		}

		// Rows with rownum < 900 are flushed and not accessible
		for (int rownum = 0; rownum < 900; rownum++) {
			Assert.assertNull(sh.getRow(rownum));
		}

		// ther last 100 rows are still in memory
		for (int rownum = 900; rownum < 1000; rownum++) {
			Assert.assertNotNull(sh.getRow(rownum));
		}

		FileOutputStream out = new FileOutputStream(fileName);
		wb.write(out);
		out.close();

		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}
}
