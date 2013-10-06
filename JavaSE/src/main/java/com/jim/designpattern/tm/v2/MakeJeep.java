package com.jim.designpattern.tm.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MakeJeep extends MakeCar {
    //组装车头
    protected void makeHead() {
        System.out.println("Make Jeep head.");
    }

    //组装车身
    protected void makeBody() {
        System.out.println("Make Jeep body.");
    }

    //组装车尾
    protected void makeTail() {
        System.out.println("Make Jeep tail.");
    }

    //测试
    protected boolean checkMake() {
        return true;
    }
}
