package com.jim.designpattern.abstractfactory.v3.gt;

import com.jim.designpattern.abstractfactory.v3.base.Car;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/2/13
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GTCar implements Car {
    @Override
    public void run() {
        System.out.println("gt car running...");
    }
}
