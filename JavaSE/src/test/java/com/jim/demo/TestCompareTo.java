package com.jim.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestCompareTo {

    @Test
    public void test() {
        String[] array = new String[]{"lilei", "libai", "james", "poly", "wobfei"};
        String[] array2 = array.clone();
        System.out.println(array);
        System.out.println(array2);
        List<String> asList = Arrays.asList(array);
        Collections.sort(asList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        Collections.sort(asList);
        Collections.reverse(asList);
        for (String s : asList) {
            System.out.println(s);
        }
    }

    @Test
    public void testSort() throws Exception {
        Integer[] array = new Integer[]{10, 9, null, null, null, 5, null, 12, 13, null};
        List<Integer> asList = Arrays.asList(array);
        Collections.sort(asList, new BETComparator());
        System.out.println("======================");
        for (Integer s : asList) {
            System.out.println(s);
        }
    }

    public static class BETComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer numOne, Integer numTwo) {
            System.out.println("numOne = " + numOne + " numTwo = " + numTwo);
            if (numOne == null) {
                return 1;
            } else if (numTwo == null) {
                return -1;
            } else {
                return numTwo - numOne;
            }
        }
    }
}
