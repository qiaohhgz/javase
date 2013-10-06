package com.jim.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/16/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestStringUtils {
    @Test
    public void testFilterHTML() throws Exception {
        String value = StringUtils.filterHTML("<html> Hello \nWorld </html>");
        assertEquals(value, "&lt;html&gt;&nbsp;Hello&nbsp;<br/>World&nbsp;&lt;/html&gt;");
    }
}
