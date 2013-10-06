package com.jim.designpattern.abstractfactory.v3.base;

import com.jim.designpattern.abstractfactory.v3.gt.GTCarFactory;
import com.jim.designpattern.abstractfactory.v3.suv.SUVCarFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/2/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    public static void main(String[] args) {
        CarFactory suvCarFactory = new SUVCarFactory();
        CarFactory gtCarFactory = GTCarFactory.getInstance();
        ApControl control = new ApControl(gtCarFactory);
        control.run();
    }
}
