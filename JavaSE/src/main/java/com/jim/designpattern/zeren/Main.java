package com.jim.designpattern.zeren;

import java.util.ArrayList;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> stuList = new ArrayList<Student>();

		Classroom cr1 = Classroom.ONE;

		Instructor t1 = new Instructor();

		Student stu1 = new RegularStudent();
		Student stu2 = new RegularStudent();
		Student stu3 = new RegularStudent();
		Student stu4 = new RegularStudent();

		stuList.add(stu1);
		stuList.add(stu2);
		stuList.add(stu3);
		stuList.add(stu4);

		t1.tellStudentsGotoNextClassroom(stuList);
	}
}
