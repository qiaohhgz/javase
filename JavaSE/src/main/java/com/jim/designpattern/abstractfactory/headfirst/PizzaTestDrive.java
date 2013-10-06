package com.jim.designpattern.abstractfactory.headfirst;

public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        chicagoStore.orderPizza("cheese");
        System.out.println("\n\n\n");
        chicagoStore.orderPizza("clam");
    }
}
