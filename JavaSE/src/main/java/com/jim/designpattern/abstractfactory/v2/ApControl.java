package com.jim.designpattern.abstractfactory.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/1/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApControl {
    private DisplayDriver displayDriver;
    private PrintDriver printDriver;

    private ResFactory factory;

    public ApControl(ResFactory factory) {
        this.factory = factory;
        this.displayDriver = factory.getDisplayDriver();
        this.printDriver = factory.getPrintDriver();
    }

    public void draw() {
        displayDriver.draw();
    }

    public void print() {
        printDriver.print();
    }

    public DisplayDriver getDisplayDriver() {
        return displayDriver;
    }

    public void setDisplayDriver(DisplayDriver displayDriver) {
        this.displayDriver = displayDriver;
    }

    public PrintDriver getPrintDriver() {
        return printDriver;
    }

    public void setPrintDriver(PrintDriver printDriver) {
        this.printDriver = printDriver;
    }
}
