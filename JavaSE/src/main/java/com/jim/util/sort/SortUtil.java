package com.jim.util.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SortUtil {
    private SortType DEFAULT_SORT = SortType.bubble;
    private static SortUtil instance = new SortUtil();
    private Map<SortType, Sort> map = new HashMap<SortType, Sort>();

    private SortUtil() {
        map.put(SortType.bubble, new BubbleSort());
        map.put(SortType.insert, new InsertSort());
        map.put(SortType.heap, new HeapSort());
        map.put(SortType.merge, new MergeSort());
        map.put(SortType.selection, new SelectionSort());
        map.put(SortType.shell, new ShellSort());
    }

    public enum SortType {
        /**
         * 冒泡排序
         */
        bubble,
        /**
         *
         */
        insert,
        heap,//
        merge,//
        selection,//
        shell//
    }

    public void sort(int[] data, Sort sort) {
        sort.sort(data);
    }

    public static void sort(int[] data) {
        instance.sort(data, instance.DEFAULT_SORT);
    }

    public static void sort(int[] data, SortType type) {
        instance.map.get(type).sort(data);
    }
}


