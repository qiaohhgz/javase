package com.jim.designpattern.factory.v1;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-1
 * Time: 下午10:10
 * To change this template use File | Settings | File Templates.
 */
public class Car {
    private static Car ourInstance = new Car();

    public synchronized static Car getInstance() {
        return ourInstance;
    }

    private Car() {
    }
}
