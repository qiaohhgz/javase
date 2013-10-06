package com.jim.demo.lock.d03;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Customer implements Runnable {
    private final String name;
    private final CustomerService customerService;
    private int callTime;

    public Customer(String name, CustomerService customerService, int callTime) {
        this.name = name;
        this.customerService = customerService;
        this.callTime = callTime;
    }

    @Override
    public void run() {
        try {
            customerService.call(this);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public static void main(String[] args) {
        CustomerService service = new CustomerService();
        service.addCustomer(new Customer("Tom", service, 10000));
        service.addCustomer(new Customer("Jerry", service, 5000));
        service.addCustomer(new Customer("Kevin", service, 3000));
        service.addCustomer(new Customer("Jim", service, 2000));
        new Thread(service).start();
    }

    public int getCallTime() {
        return callTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
