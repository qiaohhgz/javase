package com.jim.designpattern.tm.v4;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class SalesET extends AbstractEstimatorTemplate<SalesResultBean> {

    @Override
    protected SalesResultBean calcResult(String apiResult) {
        SalesResultBean bean = new SalesResultBean();
        bean.setResult(String.format("Sales result bean %s.", apiResult));
        return bean;
    }
}
