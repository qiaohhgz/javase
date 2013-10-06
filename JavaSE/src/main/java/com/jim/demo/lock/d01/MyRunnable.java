package com.jim.demo.lock.d01;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyRunnable implements Runnable {
    @Override
    public synchronized void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}
