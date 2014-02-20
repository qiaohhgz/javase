package com.jim.demo.math;

import java.applet.Applet;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 1/13/14
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld extends Applet {
    public static void main(String[] args) {

        System.out.println("sin 0 = " + Math.sin(Math.toRadians(0)));
        System.out.println("sin 30 = " + Math.sin(Math.toRadians(30)));
        System.out.println("sin 45 = " + Math.sin(Math.toRadians(45)));
        System.out.println("sin 60 = " + Math.sin(Math.toRadians(60)));
        System.out.println("sin 90 = " + Math.sin(Math.toRadians(90)));

        double sin = Math.sin(Math.toRadians(45));
        double a = 15;
        double v = a / sin;
        System.out.println(v);
    }
}
