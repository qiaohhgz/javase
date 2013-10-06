package com.jim.designpattern.abstractfactory.v3.gt;

import com.jim.designpattern.abstractfactory.v3.base.Car;
import com.jim.designpattern.abstractfactory.v3.base.CarFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/2/13
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GTCarFactory extends CarFactory {
    private static final CarFactory factory = new GTCarFactory();
    private Car car = new GTCar();

    private GTCarFactory() {

    }

    public static synchronized CarFactory getInstance() {
        return factory;
    }

    @Override
    public Car createCar() {
        return car;
    }
}
