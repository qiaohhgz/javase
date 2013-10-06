package com.jim.designpattern.abstractfactory.explained;

public abstract class ResFactory {
    abstract public DisplayDriver getDisplayDriver();
    abstract public PrintDriver getPrintDriver();
}
