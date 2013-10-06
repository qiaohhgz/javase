package com.jim.demo.excel;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class BigExcelDemoTest {

	BigExcelDemo b = new BigExcelDemo();
	String fileName = "C:/Users/user/Desktop/AdvertID-DestinationURL-DisplayURL.xlsx";

//	@Test
	public void testReadBigExcel() throws Exception {
		b.writeBigExcel("report.xlsx");

	}

	

}
