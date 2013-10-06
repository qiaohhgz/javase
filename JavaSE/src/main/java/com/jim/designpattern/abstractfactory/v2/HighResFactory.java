package com.jim.designpattern.abstractfactory.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/1/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class HighResFactory extends ResFactory {
    @Override
    public DisplayDriver getDisplayDriver() {
        return new HRDD();
    }

    @Override
    public PrintDriver getPrintDriver() {
        return new HRPD();
    }
}
