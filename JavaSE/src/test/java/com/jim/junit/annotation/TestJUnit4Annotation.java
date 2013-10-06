package com.jim.junit.annotation;

import org.junit.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/31/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestJUnit4Annotation {
    @Before
    public void testBefore() throws Exception {
        System.out.println("-----Before-----");
    }

    @After
    public void testAfter() throws Exception {
        System.out.println("-----After-----");
    }

    @BeforeClass
    public static void testBeforeClass() throws Exception {
        System.out.println("==========Before class==========");
    }

    @AfterClass
    public static void testAfterClass() throws Exception {
        System.out.println("==========After class==========");
    }

    @Test
    public void testMethodOne() throws Exception {
        System.out.println("Test Method one");
    }

    @Test
    public void testMethodTwo() throws Exception {
        System.out.println("Test Method two");
    }

    @Test
    public void testMethodThree() throws Exception {
        System.out.println("Test Method three");
    }

    @Test
    @Ignore
    public void testIgnore() throws Exception {
        System.out.println("Testing Ignore Method");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExpected() throws Exception {
        throw new IndexOutOfBoundsException();
    }
}
