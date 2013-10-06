package com.jim.util.sort;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertSort extends SortAbstract {

    @Override
    public void sort(int[] data) {
        int temp;
        for (int i = 1; i < data.length; i++) {
            for (int j = i; (j > 0) && (data[j] < data[j - 1]); j--) {
                swap(data, j, j - 1);
            }
        }
    }
}


