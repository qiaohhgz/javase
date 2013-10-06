package com.jim.designpattern.adapter;

/**
 * 适配器
 * @author qiao
 */
public class KentAdapter {
	private Kent kent = new Kent();

	public String getChinese(String english) {
		return kent.getChinese(english);
	}

	public String getEnglish(String chinese) {
		return kent.getEnglish(chinese);
	}
}
