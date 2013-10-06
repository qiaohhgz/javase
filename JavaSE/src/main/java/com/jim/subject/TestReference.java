package com.jim.subject;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/20/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestReference {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        add(a, b);
        System.out.println(a);
        System.out.println(b);

        Integer c = 5;
        Integer d = 6;
        add(c, d);
        System.out.println(c);
        System.out.println(d);

        Integer e = 3;
        int f = 4;
        add(e, f);
        System.out.println(e);
        System.out.println(f);

        int g = 7;
        Integer h = 8;
        add(g, h);
        System.out.println(g);
        System.out.println(h);
    }

    public static void add(int a, int b) {
        System.out.println("A method");
        a = 3;
        b = 4;
    }

    public static void add(Integer a, Integer b) {
        System.out.println("B method");
        a = 5;
        b = 6;
    }

    public static void add(Integer a, int b) {
        System.out.println("C method");
        a = 1;
        b = 2;
    }

    public static void add(int a, Integer b) {
        System.out.println("D method");
        a = 5;
        b = 6;
    }
}
