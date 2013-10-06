package com.jim.designpattern.zeren;

/**
 * 普通学生抽象类
 * 
 * @author jim_qiao
 * 
 */
public abstract class Student {

	/**
	 * 知道自己所在的教室
	 * 
	 * @return
	 */
	public abstract Classroom getCurrentClassroom();

	/**
	 * 知道自己下堂课的教室
	 * 
	 * @return
	 */
	public abstract Classroom getNextClassroom();

	/**
	 * 从一个教室去下一个教室
	 */
	public abstract void gotoNextClassroom();
}
