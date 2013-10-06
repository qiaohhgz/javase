package com.jim.designpattern.abstractfactory.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/1/13
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {
        ApControl control = new ApControl(new LowResFactory());
        control.draw();
        control.print();

        //add HighResFactory,HRDD,HRPD theses class
        ApControl highControl = new ApControl(new HighResFactory());
        highControl.draw();
        highControl.print();
    }
}
