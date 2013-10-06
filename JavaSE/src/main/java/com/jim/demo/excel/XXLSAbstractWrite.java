package com.jim.demo.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XXLSAbstractWrite {
	public static InputStream getInputStream(String filename) throws IOException, OpenXML4JException {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		return r.getWorkbookData();
	}

	public static void main(String[] args) throws InvalidFormatException, IOException, SAXException, ParserConfigurationException {
		String fileName = "C:/Users/user/Desktop/AdvertID-DestinationURL-DisplayURL.xlsx";
//		fileName = resource.getFile();
		System.out.println("opening");
		OPCPackage pkg = OPCPackage.open(fileName);
		System.out.println("opened");
		
		ArrayList<PackagePart> parts = pkg.getParts();
		
		for (PackagePart packagePart : parts) {
			String name = packagePart.getPartName().getName();
		}
		
		pkg.close();
		System.out.println("end");
	}
	
	public static void exportExcelNew(OutputStream out, List<List<String>> rowsData, File file) throws IOException, OpenXML4JException {
		final int rowAccessWindowSize = 10000;
		new XSSFWorkbook().write(new FileOutputStream(file));
		SXSSFWorkbook work = new SXSSFWorkbook(new XSSFWorkbook(new FileInputStream(file)), rowAccessWindowSize);
		Sheet sheet = work.createSheet("Sheet1");
		Row row = null;
		Cell cell = null;
		List<String> rowData = null;
		String value = null;
		for (int i = 0; i < rowsData.size(); i++) {
			rowData = rowsData.get(i);
			row = sheet.createRow(i);
			for (int j = 0; j < rowData.size(); j++) {
				cell = row.createCell(j);
				value = rowData.get(j);
				try {
					cell.setCellValue(Integer.parseInt(value));
				} catch (Exception e) {
					cell.setCellValue(value);
				}
			}
		}
		work.write(out);
	}
}
