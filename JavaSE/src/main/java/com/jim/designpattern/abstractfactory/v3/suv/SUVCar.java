package com.jim.designpattern.abstractfactory.v3.suv;

import com.jim.designpattern.abstractfactory.v3.base.Car;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/2/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SUVCar implements Car {

    @Override
    public void run() {
        System.out.println("suv car run...");
    }
}
