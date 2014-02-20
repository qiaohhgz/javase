package com.jim.demo.lock.d05.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/10/14
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Semaphore {
    private int signal = 0;

    public synchronized void take() {
        this.signal++;
        notify();
        System.out.println("take");
    }

    public synchronized void release() throws InterruptedException {
        while (signal == 0) wait();
        this.signal--;
        System.out.println("release");
    }
}
