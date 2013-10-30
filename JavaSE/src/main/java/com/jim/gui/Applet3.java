package com.jim.gui;

import java.applet.Applet;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class Applet3 extends Applet {
    private int init;
    private int start;
    private int stop;

    @Override
    public void init() {
        init++;
    }

    @Override
    public void start() {
        start++;
    }

    @Override
    public void stop() {
        stop++;
    }

    @Override
    public void paint(Graphics g) {
        String s = String.format("init:%d start:%d stop:%d", init, start, stop);
        g.drawString(s, 10, 10);
    }
}
