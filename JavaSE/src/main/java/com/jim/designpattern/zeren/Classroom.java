package com.jim.designpattern.zeren;

/**
 * 教室<br>
 * 有明确地址<br>
 * 
 * @author jim_qiao <br>
 * 
 */
public class Classroom {
	public static final Classroom ONE = new Classroom("一班");
	public static final Classroom TWO = new Classroom("二班");
	public static final Classroom THR = new Classroom("三班");
	public static final Classroom FOU = new Classroom("四班");
	private String address;

	public Classroom(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
