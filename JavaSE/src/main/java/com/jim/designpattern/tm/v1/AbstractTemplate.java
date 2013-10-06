package com.jim.designpattern.tm.v1;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTemplate {

    public final void getResult() {
        init();
        callApi();
        calcResult();
        resetDisplayResult();
    }

    protected abstract void init();

    protected final void callApi() {
        System.out.println("Call google api ...");
    }

    protected final void calcResult() {
        System.out.println("Calc result ...");
    }

    //hook method
    protected void resetDisplayResult() {
    }
}
