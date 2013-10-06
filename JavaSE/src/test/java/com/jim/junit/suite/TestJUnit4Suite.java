package com.jim.junit.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/31/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Suite.class)
@SuiteClasses({TestClassOne.class, TestClassTwo.class})
public class TestJUnit4Suite {
    //not execute test method

    @BeforeClass
    public static void testBeforeClass() throws Exception {
        //TODO Before
        System.out.println("Testing BeforeClass Method");
    }

    @AfterClass
    public static void testAfterClass() throws Exception {
        //TODO AfterClass
        System.out.println("Testing AfterClass Method");
    }
}
