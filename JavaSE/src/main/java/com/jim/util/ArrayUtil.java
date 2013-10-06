package com.jim.util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtil {
    public static void printList(List<? extends Object> list) {
        for (Object obj : list) {
            System.out.println(obj.toString());
        }
    }
}
