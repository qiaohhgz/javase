package com.jim.util.sort;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectionSort extends SortAbstract {
    @Override
    public void sort(int[] data) {
        int temp;
        for (int i = 0; i < data.length; i++) {
            int lowIndex = i;
            for (int j = data.length - 1; j > i; j--) {
                if (data[j] < data[lowIndex]) {
                    lowIndex = j;
                }
            }
            swap(data, i, lowIndex);
        }
    }
}
