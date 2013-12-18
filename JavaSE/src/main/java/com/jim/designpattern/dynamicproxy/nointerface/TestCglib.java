package com.jim.designpattern.dynamicproxy.nointerface;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestCglib {
    public static void main(String[] args) {
        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacadeImpl bookCglib = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
        bookCglib.addBook();
    }
}
