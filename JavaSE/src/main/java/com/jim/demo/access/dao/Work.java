package com.jim.demo.access.dao;

import java.util.Date;

public class Work {
	private Date day;
	private Date beginDate;
	private Date endDate;
	private double hours;

	public Work() {
	}

	public Work(Date day, Date beginDate, Date endDate, double hours) {
		this.day = day;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.hours = hours;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
}
