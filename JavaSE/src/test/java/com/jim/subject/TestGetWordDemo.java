package com.jim.subject;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestGetWordDemo {
    @Test
    public void testGetWordIndexList() throws Exception {
        String s = "  I am OOO tomorrow.";
        GetWordDemo manager = new GetWordDemo();
        manager.findIndex(s);
        List<Integer> wordIndexList = manager.getWordIndexList();
        assertEquals(wordIndexList.get(0).intValue(), 2);
    }
}
