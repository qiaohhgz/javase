package com.jim.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestGif {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file1 = new File("C:/Users/Jim_qiao/Desktop/TempImage/doc.pdf");
		File file2 = new File("C:/Users/Jim_qiao/Desktop/TempImage/222.png");
		File file3 = new File("C:/Users/Jim_qiao/Desktop/TempImage/test.png");
		FileInputStream fis1 = new FileInputStream(file1);
		FileInputStream fis2 = new FileInputStream(file2);
		FileOutputStream fos1 = new FileOutputStream(file3);
		byte[] bs1 = new byte[1024];
		byte[] bs2 = new byte[1024];

		StringBuffer str1 = new StringBuffer();
		StringBuffer str2 = new StringBuffer();
		for (int i1 = fis1.read(bs1); i1 != -1; i1 = fis1.read(bs1)) {
			fos1.write(bs1, 0, i1);
			for (int j = 0; j < i1; j++) {
				str1.append(bs1[j] + " ");
			}
			System.out.println(new String(bs1, 0, i1));
			break;
		}
		// str1.reverse();
		System.out.println(str1.toString());
		System.out.println("===============================");
		for (int i2 = fis2.read(bs2); i2 != -1; i2 = fis2.read(bs2)) {
			fos1.write(bs2, 0, i2);
			for (int j = 0; j < i2; j++) {
				str2.append(bs2[j] + " ");
			}
			break;
		}
		// str2.reverse();
		System.out.println(str2.toString());
		// for (int i = fis.read(bs); i != -1;i = fis.read(bs)) {
		// for (int j = 0; j < i; j++) {
		// System.out.printList(bs[j] + " ");
		// }
		// System.out.println();
		// }
		// fis.close();

		fos1.close();
		fis1.close();
		fis2.close();

		System.out.println(new String(new byte[] { -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0 }));
		System.out.println(new String(new byte[] { 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126 }));
	}
}
