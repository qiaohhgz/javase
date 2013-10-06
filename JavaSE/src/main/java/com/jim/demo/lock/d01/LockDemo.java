package com.jim.demo.lock.d01;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class LockDemo {
    private static final Logger log = Logger.getLogger(LockDemo.class);

    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}
