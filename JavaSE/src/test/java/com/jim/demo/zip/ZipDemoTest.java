package com.jim.demo.zip;

import java.io.IOException;

import org.dom4j.DocumentException;

public class ZipDemoTest {
	String fileNameBig = "C:/Users/user/Desktop/AdvertID-DestinationURL-DisplayURL.xlsx";
	String fileNameTest = "report.xlsx";
	ZipDemo demo = new ZipDemo();

//	@Test
	public void testReader() throws IOException, InterruptedException, DocumentException {
		demo.reader(fileNameBig);
	}

}
