package com.jim.designpattern.singleton;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/19/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MySingleton {
    private static MySingleton ourInstance = new MySingleton();

    public static MySingleton getInstance() {
        System.out.println("MySingleton.getInstance()");
        return ourInstance;
    }

    private MySingleton() {
    }
}
