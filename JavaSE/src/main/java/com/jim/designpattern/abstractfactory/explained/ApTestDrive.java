package com.jim.designpattern.abstractfactory.explained;

public class ApTestDrive {

    public static void main(String[] args) {
        ApControl apControl = new ApControl(new HighResFact());

        apControl.draw();
        apControl.print();
    }
}
