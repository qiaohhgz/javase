package com.jim.util.sort;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 * 冒泡排序
 */
public class BubbleSort extends SortAbstract {

    @Override
    public void sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j <= data.length - 1; j++) {
                if (data[i] > data[j]) {
                    swap(data, i, j);
                }
            }
        }
    }
}
