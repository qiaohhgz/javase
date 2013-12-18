package com.jim.designpattern.dynamicproxy.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacadeImpl target = new BookFacadeImpl();
        target.addBook();
        BookFacade bookProxy = proxy.bind(target);
        bookProxy.addBook();
    }
}
