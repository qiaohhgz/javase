package com.jim.designpattern.tm.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MakeCar {
    //组装车头
    protected abstract void makeHead();

    //组装车身
    protected abstract void makeBody();

    //组装车尾
    protected abstract void makeTail();

    //测试
    protected abstract boolean checkMake();

    public void make() {
        System.out.println("Start make car...");
        makeHead();
        makeBody();
        makeTail();
        if (checkMake()) {
            System.out.println("Make OK.");
        } else {
            System.out.println("Make Failure.");
        }
    }
}
