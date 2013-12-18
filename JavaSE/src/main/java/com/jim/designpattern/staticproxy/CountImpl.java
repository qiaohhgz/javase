package com.jim.designpattern.staticproxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountImpl implements Countable {
    @Override
    public void queryCount() {
        System.out.println("查看账户方法...");
    }

    @Override
    public void updateCount() {
        System.out.println("修改账户方法...");
    }
}
