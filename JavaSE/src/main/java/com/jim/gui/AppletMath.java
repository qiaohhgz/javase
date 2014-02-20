package com.jim.gui;

import java.applet.Applet;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 1/13/14
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppletMath extends Applet {
    @Override
    public void init() {
        super.init();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.

        int offsetX = 100;
        int offsetY = 100;
        int xLen = 10;
        for (int i = 10; i < 90; i += 10) {
            // right triangle
            int radians = i;
            int x = xLen + offsetX;
            int y = (int) (Math.tan(Math.toRadians(radians)) * x) + offsetY;
            Point o = new Point(offsetX, offsetY);
            Point b = new Point(x, 0 + offsetY);
            Point c = new Point(x, y);
            // o -> b
            drawLine(g, b, c, Color.RED);
            // c -> b
            drawLine(g, o, b, Color.GREEN);
            // o -> c
            drawLine(g, o, c, Color.BLUE);
        }
    }

    private Graphics drawLine(Graphics g, Point a, Point b, Color color) {
        g.setColor(color);
        g.drawLine(a.x, a.y, b.x, b.y);
        return g;
    }
}
