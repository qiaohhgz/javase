package com.jim.util.sort;


import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestSortUtil {
    int[] data = {95, 68, 55, 37, 99, 74, 21, 21, 32, 77};

    @Test
    public void testBobbleSort() {
        SortUtil.sort(data, SortUtil.SortType.bubble);
        print(data);
    }

    @Test
    public void testInsertSort() throws Exception {
        SortUtil.sort(data, SortUtil.SortType.insert);
        print(data);
    }

    private void print(int[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(data[i]);
        }
        System.out.println(sb.toString());
    }
}
