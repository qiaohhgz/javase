package com.jim.util.sort;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class SortAbstract implements Sort {
    /**
     * @param data
     * @param i
     * @param j
     */
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * @param data
     */
    public static void print(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println("");
    }
}
