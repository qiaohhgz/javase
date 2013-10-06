package com.jim.designpattern.zeren;

import java.util.List;

/**
 * 教师
 * 
 * @author jim_qiao
 * 
 */
public class Instructor {

	/**
	 * 告诉学生到下堂课的教室去
	 */
	public void tellStudentsGotoNextClassroom(List<Student> list) {
		for (Student stu : list) {
			stu.gotoNextClassroom();
		}
	}
}
