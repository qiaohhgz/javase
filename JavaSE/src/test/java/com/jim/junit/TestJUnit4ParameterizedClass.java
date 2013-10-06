package com.jim.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/31/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class TestJUnit4ParameterizedClass {
    private int multiplier;

    public TestJUnit4ParameterizedClass(int testParameter) {
        System.out.println("testParameter = " + testParameter);
        this.multiplier = testParameter;
    }

    // Creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        System.out.println("Test data method");
        Object[][] data = new Object[][]{{1}, {5}, {121}};
        return Arrays.asList(data);
    }

    @Test
    public void testMultiplyException() {
        System.out.println("Test MultiplyException Method");
        MyClass tester = new MyClass();
        assertEquals("Result", multiplier * multiplier, tester.multiply(multiplier, multiplier));
    }

}
