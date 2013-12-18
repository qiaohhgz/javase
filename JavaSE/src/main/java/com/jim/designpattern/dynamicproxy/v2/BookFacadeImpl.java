package com.jim.designpattern.dynamicproxy.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookFacadeImpl implements BookFacade {
    @Override
    public void addBook() {
        System.out.println("增加图书方法。。。");
    }

    @Override
    public void getBookById() {
        System.out.println("读取图书方法。。。");
    }

    @Override
    public void searchBookByFilter() {
        System.out.println("查找图书方法。。。");
    }
}
