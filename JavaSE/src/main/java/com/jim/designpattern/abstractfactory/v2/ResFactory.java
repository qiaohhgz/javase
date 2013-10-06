package com.jim.designpattern.abstractfactory.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/1/13
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ResFactory {
    abstract public DisplayDriver getDisplayDriver();

    abstract public PrintDriver getPrintDriver();
}
