package com.jim.memory;

import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 6/13/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestJvmMemoryCheck {

    @Test(timeout = 3000)
    public void testCheck() throws Exception {
        JvmMemoryCheck memory = new JvmMemoryCheck();
        memory.check();
    }

    @Test
    @Ignore
    public void testIgnore() throws Exception {
        assertTrue(false);
    }
}
