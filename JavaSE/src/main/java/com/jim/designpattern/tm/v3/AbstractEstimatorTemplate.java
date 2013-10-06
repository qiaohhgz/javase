package com.jim.designpattern.tm.v3;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractEstimatorTemplate {
    protected Object other;

    public final Object getEstimator(Object params) throws Exception {
        try {
            doInit();

            Object apiResult = callApi(params);

            Object calcResult = calcResult(apiResult);

            Object finalResult = resetDisplayResult(calcResult);

            return finalResult;
        } catch (Exception ex) {
            if (doCatchException(ex)) {
                throw ex;
            }
            return null;
        } finally {
            doDestroy();
        }
    }


    //hook method
    protected void doInit() {
    }

    protected final Object callApi(Object params) {
        System.out.println("Call google api ...");
        //code
        return null;
    }

    protected final Object calcResult(Object apiResult) {
        System.out.println("Calc result ...");
        //code
        return null;
    }

    //hook method
    protected Object resetDisplayResult(Object calcResult) {
        return calcResult;
    }

    //hook method
    protected boolean doCatchException(Exception ex) {
        return true;
    }

    //hook method
    protected void doDestroy() {

    }

    protected Object getOther() {
        return other;
    }

    protected void setOther(Object other) {
        this.other = other;
    }
}
