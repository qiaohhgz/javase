package com.jim.demo.lock.d05.v1;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/10/14
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Semaphore {
    private boolean signal = false;

    public synchronized void take() {
        this.signal = true;
        System.out.println("take");
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (!signal) wait();
        this.signal = false;
        System.out.println("release");
    }
}
