package com.jim.designpattern.zeren;

/**
 * 普通学生
 * 
 * @author jim_qiao
 * 
 */
public class RegularStudent extends Student {
	private Classroom currentClassroom;
	private Classroom nextClassroom;

	@Override
	public Classroom getCurrentClassroom() {
		// TODO Auto-generated method stub
		return currentClassroom;
	}

	@Override
	public Classroom getNextClassroom() {
		// TODO Auto-generated method stub
		return nextClassroom;
	}

	@Override
	public void gotoNextClassroom() {
		// TODO Auto-generated method stub
		String path = DirectionGiver.getClassroomPath();
		System.out.println("找到自己下堂课的教室在哪里->" + path);
		System.out.println("决定怎么去");
		System.out.println("去那里");
		this.currentClassroom = nextClassroom;
	}
}
