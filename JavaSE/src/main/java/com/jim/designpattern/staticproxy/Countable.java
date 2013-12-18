package com.jim.designpattern.staticproxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Countable {
    // 查看账户方法
    public void queryCount() throws Exception;

    // 修改账户方法
    public void updateCount() throws Exception;
}
