package com.jim.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RuntimeDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Runtime rt = Runtime.getRuntime();
		Process process = rt.exec("cmd.exe netstat");
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String msg = null;
		while ((msg = br.readLine()) != null) {
			System.out.println(msg);
		}
		br.close();
		process.destroy();
	}
}
