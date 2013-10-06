package com.jim.demo.lock.d03;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerService implements Runnable {
    private Set<Customer> customers = new HashSet<Customer>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Collection<Customer> getCustomers() {
        return Collections.unmodifiableSet(customers);
    }

    @Override
    public void run() {
        Iterator<Customer> iterator = getCustomers().iterator();
        while (iterator.hasNext()) {
            new Thread(iterator.next()).start();
        }
    }

    public synchronized void call(Customer customer) throws InterruptedException {
        System.out.println("Please wait " + customer);
        wait(customer.getCallTime());
        System.out.println("=======================");
        System.out.println("connecting..." + customer);
        System.out.println("connect " + customer);
        System.out.println("disconnect " + customer);
    }
}
