package com.jim.demo.annotation;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/12/13
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Method[] method = Book.class.getMethods();
        for (Method m : method) {
            boolean b = m.isAnnotationPresent(Undeletable.class);
            if (b) {
                Undeletable anno = (Undeletable) m.getAnnotation(Undeletable.class);
                System.out.println(anno.status());
            }
        }
    }
}
