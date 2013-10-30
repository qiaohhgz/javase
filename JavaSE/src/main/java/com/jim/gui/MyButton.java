package com.jim.gui;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 11:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyButton extends Canvas {
    AutoEvent parent;
    Color color;
    String label;

    MyButton(AutoEvent parent,
             Color color, String label) {
        this.label = label;
        this.parent = parent;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        int rnd = 30;
        g.fillRoundRect(0, 0, size().width,
                size().height, rnd, rnd);
        g.setColor(Color.black);
        g.drawRoundRect(0, 0, size().width,
                size().height, rnd, rnd);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(label);
        int height = fm.getHeight();
        int ascent = fm.getAscent();
        int leading = fm.getLeading();
        int horizMargin = (size().width - width) / 2;
        int verMargin = (size().height - height) / 2;
        g.setColor(Color.white);
        g.drawString(label, horizMargin,
                verMargin + ascent + leading);
    }

    public boolean keyDown(Event evt, int key) {
        TextField t =
                (TextField) parent.h.get("keyDown");
        t.setText(evt.toString());
        return true;
    }

    public boolean keyUp(Event evt, int key) {
        TextField t =
                (TextField) parent.h.get("keyUp");
        t.setText(evt.toString());
        return true;
    }

    public boolean lostFocus(Event evt, Object w) {
        TextField t =
                (TextField) parent.h.get("lostFocus");
        t.setText(evt.toString());
        return true;
    }

    public boolean gotFocus(Event evt, Object w) {
        TextField t =
                (TextField) parent.h.get("gotFocus");
        t.setText(evt.toString());
        return true;
    }

    public boolean
    mouseDown(Event evt, int x, int y) {
        TextField t =
                (TextField) parent.h.get("mouseDown");
        t.setText(evt.toString());
        return true;
    }

    public boolean
    mouseDrag(Event evt, int x, int y) {
        TextField t =
                (TextField) parent.h.get("mouseDrag");
        t.setText(evt.toString());
        return true;
    }

    public boolean
    mouseEnter(Event evt, int x, int y) {
        TextField t =
                (TextField) parent.h.get("mouseEnter");
        t.setText(evt.toString());
        return true;
    }

    public boolean
    mouseExit(Event evt, int x, int y) {
        TextField t =
                (TextField) parent.h.get("mouseExit");
        t.setText(evt.toString());
        return true;
    }

    public boolean
    mouseMove(Event evt, int x, int y) {
        TextField t =
                (TextField) parent.h.get("mouseMove");
        t.setText(evt.toString());
        return true;
    }

    public boolean mouseUp(Event evt, int x, int y) {
        TextField t =
                (TextField) parent.h.get("mouseUp");
        t.setText(evt.toString());
        return true;
    }
}
