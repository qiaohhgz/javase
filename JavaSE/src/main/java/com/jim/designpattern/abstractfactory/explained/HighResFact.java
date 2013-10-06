package com.jim.designpattern.abstractfactory.explained;

public class HighResFact extends ResFactory{
    @Override
    public DisplayDriver getDisplayDriver() {
        return new HRDD();
    }

    @Override
    public PrintDriver getPrintDriver() {
        return new HRPD();
    }
}
