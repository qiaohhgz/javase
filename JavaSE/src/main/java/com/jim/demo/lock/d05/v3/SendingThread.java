package com.jim.demo.lock.d05.v3;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/10/14
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendingThread extends Thread {
    private Semaphore semaphore;

    public SendingThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.semaphore.take();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
