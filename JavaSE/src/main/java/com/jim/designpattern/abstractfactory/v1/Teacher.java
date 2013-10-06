package com.jim.designpattern.abstractfactory.v1;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/31/13
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Teacher implements Say {
    @Override
    public void say() {
        System.out.println("Hello everyone.");
    }
}
