package com.jim.gui;

import java.applet.Applet;
import java.awt.*;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class AutoEvent extends Applet {
    Hashtable h = new Hashtable();
    String[] event = {
            "keyDown", "keyUp", "lostFocus",
            "gotFocus", "mouseDown", "mouseUp",
            "mouseMove", "mouseDrag", "mouseEnter",
            "mouseExit"
    };
    MyButton
            b1 = new MyButton(this, Color.blue, "test1"),
            b2 = new MyButton(this, Color.red, "test2");

    public void init() {
        setLayout(new GridLayout(event.length + 1, 2));
        for (int i = 0; i < event.length; i++) {
            TextField t = new TextField();
            t.setEditable(false);
            add(new Label(event[i], Label.CENTER));
            add(t);
            h.put(event[i], t);
        }
        add(b1);
        add(b2);
    }
}