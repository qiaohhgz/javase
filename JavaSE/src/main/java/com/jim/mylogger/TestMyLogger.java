package com.jim.mylogger;

import java.io.File;
import java.io.FileOutputStream;

public class TestMyLogger {
	public static final MyLogger log = MyLogger.getLogger(TestMyLogger.class);

	public static void main(String[] args) {
		try {
			File file = new File("A:/abc.txt");
			FileOutputStream fos = new FileOutputStream(file);
		
		} catch (Exception e) {
			log.error(e);
		}
	}
}
