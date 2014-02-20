package com.jim.demo.lock.d05.v3;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/10/14
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Semaphore {
    private int signal = 0;
    private int bound = 0;

    public Semaphore(int upperBound) {
        this.bound = upperBound;
    }

    public synchronized void take() throws InterruptedException {
        System.out.println("A");
        while (this.signal == this.bound) wait();
        this.signal++;
        notify();
        System.out.println("take");
    }

    public synchronized void release() throws InterruptedException {
        System.out.println("B");
        while (this.signal == 0) wait();
        this.signal--;
        System.out.println("release");
    }
}
