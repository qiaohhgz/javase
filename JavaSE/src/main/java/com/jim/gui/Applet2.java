package com.jim.gui;

import com.jim.util.Utils;

import java.applet.Applet;
import java.awt.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Applet2 extends Applet {
    Point p1;
    Point p2;
    int size = 10;
    List<Point> points;
    int index = 0;
    boolean start = false;

    @Override
    public void init() {
    }

    @Override
    public boolean mouseDown(Event evt, int x, int y) {
        p1 = new Point(x, y);
        return true;
    }

    @Override
    public boolean mouseUp(Event evt, int x, int y) {
        p2 = new Point(x, y);
        points = Utils.parsePath(p1, p2, size);
        start = true;
        return true;
    }

    @Override
    public void paint(Graphics g) {
        if (start) {
            System.out.println("index = " + index);
            if (index >= points.size()) {
                start = false;
                index = 0;
            } else {

                Point p = points.get(index);

                if (index + 1 < points.size()) {
                    Point last = new Point(p.x, p.y);
                    Point first = points.get(index + 1);
                    g.drawLine(last.x, last.y, first.x, first.y);
                }

                index++;
            }
        }
        try {
            Thread.sleep(50);
            repaint();
        } catch (InterruptedException e) {

        }
    }
}
