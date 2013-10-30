package com.jim.gui;

import java.applet.Applet;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppletOne extends Applet {

    @Override
    public void start() {
        super.start();    //To change body of overridden methods use File | Settings | File Templates.
        System.out.println("start");
    }

    @Override
    public void init() {
        super.init();    //To change body of overridden methods use File | Settings | File Templates.
        System.out.println("init");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw string First applet.");
        g.drawString("First applet", 10, 10);
        g.draw3DRect(0, 0, 100, 20, true);
    }


}
