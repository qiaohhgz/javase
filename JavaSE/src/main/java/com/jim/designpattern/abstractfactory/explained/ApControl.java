package com.jim.designpattern.abstractfactory.explained;

public class ApControl {
    DisplayDriver myDisplayDriver;
    PrintDriver myPrintDriver;

    ResFactory resFactory;

    public ApControl(ResFactory resFactory) {
        this.resFactory = resFactory;
        this.myDisplayDriver = resFactory.getDisplayDriver();
        this.myPrintDriver = resFactory.getPrintDriver();
    }

    public void draw(){
        myDisplayDriver.draw();
    }

    public void print(){
        myPrintDriver.print();
    }

    public DisplayDriver getMyDisplayDriver() {
        return myDisplayDriver;
    }

    public void setMyDisplayDriver(DisplayDriver myDisplayDriver) {
        this.myDisplayDriver = myDisplayDriver;
    }

    public PrintDriver getMyPrintDriver() {
        return myPrintDriver;
    }

    public void setMyPrintDriver(PrintDriver myPrintDriver) {
        this.myPrintDriver = myPrintDriver;
    }

    public ResFactory getResFactory() {
        return resFactory;
    }

    public void setResFactory(ResFactory resFactory) {
        this.resFactory = resFactory;
    }
}
