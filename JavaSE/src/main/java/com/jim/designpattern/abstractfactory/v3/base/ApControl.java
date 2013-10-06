package com.jim.designpattern.abstractfactory.v3.base;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/2/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApControl {
    private CarFactory factory;
    private Car car;

    public ApControl(CarFactory factory) {
        this.factory = factory;
        this.car = factory.createCar();
    }

    public void run(){
        this.car.run();
    }

    public CarFactory getFactory() {
        return factory;
    }

    public void setFactory(CarFactory factory) {
        this.factory = factory;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
