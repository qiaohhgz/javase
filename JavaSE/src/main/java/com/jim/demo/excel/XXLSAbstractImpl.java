package com.jim.demo.excel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class XXLSAbstractImpl extends XXLSAbstract {

	@Override
	public void optRows(int sheetIndex, int curRow, List<String> rowlist) throws SQLException {
		System.out.println(Arrays.toString(rowlist.toArray()));
	}

	public static void main(String[] args) {
		XXLSAbstractImpl x = new XXLSAbstractImpl();
		try {
			URL resource = XXLSAbstractImpl.class.getClassLoader().getResource("test.xlsx");
			String fileName = "C:/Users/user/Desktop/AdvertID-DestinationURL-DisplayURL.xlsx";
			fileName = resource.getFile();
			x.processOneSheet(fileName, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
