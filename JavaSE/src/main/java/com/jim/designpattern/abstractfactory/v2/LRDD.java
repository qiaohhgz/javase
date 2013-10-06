package com.jim.designpattern.abstractfactory.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/1/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class LRDD extends DisplayDriver {
    @Override
    public void draw() {
        System.out.println("draw from low resolution display driver.");
    }
}
