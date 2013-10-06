package com.jim.designpattern.adapter;

/**
 * 适配器模式
 * @author qiao
 */
public class Main {
	public static void main(String[] args) {
		String english = "Hello";
		System.out.println("美女说:" + english);
		//创建适配器
		KentAdapter adapter = new KentAdapter();
		//翻译成中文
		String chinese = adapter.getChinese(english);
		System.out.println("哦原来美女说[" + chinese + "]啊");
	}
}
