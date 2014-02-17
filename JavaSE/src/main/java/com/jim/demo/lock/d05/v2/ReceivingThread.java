package com.jim.demo.lock.d05.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/10/14
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReceivingThread extends Thread {
    private Semaphore semaphore;

    public ReceivingThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
