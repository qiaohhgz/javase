package com.jim.demo.lock.d04;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stack {
    LinkedList list = new LinkedList();

    public synchronized void push(Object obj) {
        synchronized (list) {
            System.out.println("add " + obj);
            list.addLast(obj);
            notify();
        }
    }

    public synchronized void pop(Object obj) throws InterruptedException {
        synchronized (list) {
            if (list.size() <= 0) {
                wait();
            }
            System.out.println("remove " + obj);
            list.removeLast();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Stack stack = new Stack();
        stack.push("abc1");
        stack.push("abc2");
        stack.push("abc3");
        stack.pop("abc1");
        stack.pop("abc2");
        stack.pop("abc3");
        stack.pop("abc4");
        stack.push("abc4");
    }
}
