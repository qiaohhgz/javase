package com.jim.designpattern.staticproxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountProxy implements Countable {
    private CountImpl countImpl;

    /**
     * 覆盖默认构造器
     *
     * @param countImpl
     */
    public CountProxy(CountImpl countImpl) {
        this.countImpl = countImpl;
    }

    @Override
    public void queryCount() {
        System.out.println("事务处理之前");
        try {
            // 调用委托类的方法;
            countImpl.queryCount();
        } finally {
            System.out.println("事务处理之后");
        }
    }

    @Override
    public void updateCount() {
        System.out.println("事务处理之前");

        try {
            // 调用委托类的方法;
            countImpl.updateCount();
        } finally {
            System.out.println("事务处理之后");
        }
    }
}
