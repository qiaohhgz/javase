package com.jim.util.sort;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShellSort extends SortAbstract {
    @Override
    public void sort(int[] data) {
        for (int i = data.length / 2; i > 2; i /= 2) {
            for (int j = 0; j < i; j++) {
                insertSort(data, j, i);
            }
        }
        insertSort(data, 0, 1);
    }

    private void insertSort(int[] data, int start, int inc) {
        int temp;
        for (int i = start + inc; i < data.length; i += inc) {
            for (int j = i; (j >= inc) && (data[j] < data[j - inc]); j -= inc) {
                swap(data, j, j - inc);
            }
        }
    }
}
