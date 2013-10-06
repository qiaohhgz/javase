package com.jim.demo.excel;

import org.junit.Test;

public class TestExampleEventUserModel {
	String fileName = "C:/Users/user/Desktop/AdvertID-DestinationURL-DisplayURL.xlsx";
	String fileNameTest = "C:/Users/user/Desktop/test.xlsx";
	ExampleEventUserModel eeum = new ExampleEventUserModel();

//	@Test
	public void testProcessOneSheet() throws Exception {
		eeum.processOneSheet(fileNameTest);
	}

	@Test
	public void testProcessAllSheets() {
	}
}
