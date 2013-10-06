package com.jim.util;

import com.jim.google.adwords.util.KeywordTextUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestKeywordTextUtil {
    private char[] except = "&!@%^*()={};~`<>?\\|".toCharArray();

    @Test
    public void testGetNormalizeKeywordText() throws Exception {
        for (char c : except) {
            assertEquals(KeywordTextUtil.getNormalizeKeywordText("Florist " + c), "florist");
        }
    }
}
