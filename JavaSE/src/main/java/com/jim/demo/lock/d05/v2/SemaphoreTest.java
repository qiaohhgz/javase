package com.jim.demo.lock.d05.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/10/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        SendingThread sender = new SendingThread(semaphore);
        ReceivingThread receiver = new ReceivingThread(semaphore);
        sender.start();
        receiver.start();
    }
}
