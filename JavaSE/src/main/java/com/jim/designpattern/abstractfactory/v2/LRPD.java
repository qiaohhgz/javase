package com.jim.designpattern.abstractfactory.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/1/13
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class LRPD extends PrintDriver {
    @Override
    public void print() {
        System.out.println("printList from low resolution printList driver.");
    }
}
