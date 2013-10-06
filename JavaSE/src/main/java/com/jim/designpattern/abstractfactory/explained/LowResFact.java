package com.jim.designpattern.abstractfactory.explained;

public class LowResFact extends ResFactory{
    @Override
    public DisplayDriver getDisplayDriver() {
        return new LRDD();
    }

    @Override
    public PrintDriver getPrintDriver() {
        return new LRPD();
    }
}
