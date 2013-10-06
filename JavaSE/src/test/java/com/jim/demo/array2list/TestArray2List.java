package com.jim.demo.array2list;

import com.jim.util.ArrayUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestArray2List {
    private String[] data;

    @Before
    public void init() throws Exception {
        data = new String[]{"A", "B", "C"};
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testArrayToReadOnlyList() throws Exception {
        List<String> strings = Arrays.asList(data);
        strings.add("D");// throw UnsupportedOperationException
    }


    /**
     * theory
     *
     * @see #testArrayToWriteListModel()
     */
    @Test
    public void testArrayToWriteList() throws Exception {
        List<String> strings = new ArrayList<String>(Arrays.asList(data));
        strings.add("D");
        ArrayUtil.printList(strings);
    }

    @Test
    public void testArrayToWriteListModel() throws Exception {
        List<String> data = new ArrayList<String>(3);
        data.add("A");
        data.add("B");
        data.add("C");

        List<String> strings = new ArrayList<String>(data);
        strings.add("D");

        ArrayUtil.printList(strings);
    }

    @Test
    public void testArrayToWriteListBase() throws Exception {
        List<String> data = new ArrayList<String>(3);
        data.add("A");
        data.add("B");
        data.add("C");

        List<String> strings = new ArrayList<String>();
        strings.addAll(data);
        strings.add("D");

        ArrayUtil.printList(strings);
    }
}
