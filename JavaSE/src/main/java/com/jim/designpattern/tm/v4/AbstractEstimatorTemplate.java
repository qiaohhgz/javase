package com.jim.designpattern.tm.v4;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractEstimatorTemplate<T> {

    public final T getEstimator(String params) throws Exception {
        String apiResult = callApi(params);

        T calcResult = calcResult(apiResult);

        T finalResult = resetDisplayResult(calcResult);

        return finalResult;
    }

    protected final String callApi(String params) {
        System.out.println("Call google api ...");
        //code
        return params;
    }

    protected abstract T calcResult(String apiResult);


    //hook method
    protected T resetDisplayResult(T calcResult) {
        return calcResult;
    }
}
