package com.jim.designpattern.abstractfactory.v3.suv;

import com.jim.designpattern.abstractfactory.v3.base.Car;
import com.jim.designpattern.abstractfactory.v3.base.CarFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/2/13
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class SUVCarFactory extends CarFactory {
    @Override
    public Car createCar() {
        return new SUVCar();
    }
}
