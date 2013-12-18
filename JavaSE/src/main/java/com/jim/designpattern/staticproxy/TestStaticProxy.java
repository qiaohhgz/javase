package com.jim.designpattern.staticproxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestStaticProxy {
    public static void main(String[] args) {
        CountImpl countImpl = new CountImpl();
        CountProxy countProxy = new CountProxy(countImpl);
        countProxy.updateCount();
        countProxy.queryCount();

    }
}
