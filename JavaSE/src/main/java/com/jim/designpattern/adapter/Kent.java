package com.jim.designpattern.adapter;

/**
 * @author qiao
 */
public class Kent {
	public String getChinese(String english) {
		//具体功能实现
		return "你好";
	}
	public String getEnglish(String chinese) {
		//具体功能实现
		return "Hello";
	}
}
