package com.jim.designpattern.tm.v4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/23/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestSalesET {
    AbstractEstimatorTemplate<SalesResultBean> template = new SalesET();

    @Test
    public void testGetEstimator() throws Exception {
        SalesResultBean estimator = template.getEstimator("{id:1000}");
        String result = estimator.getResult();
        System.out.println("result = " + result);
        assertEquals(result, "Sales result bean {id:1000}.");
    }
}
