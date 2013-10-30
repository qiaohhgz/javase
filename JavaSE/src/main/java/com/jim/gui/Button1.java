package com.jim.gui;

import java.applet.Applet;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Button1 extends Applet {
    private Button b1 = new Button("Button 1"), b2 = new Button("Button 2");

    @Override
    public void init() {
        add(b1);
        add(b2);
    }

    @Override
    public boolean action(Event evt, Object what) {
        if (evt.target.equals(b1)) {
            getAppletContext().showStatus("Button 1");
        } else if (evt.target.equals(b2)) {
            getAppletContext().showStatus("Button 2");
        } else {
            return super.action(evt, what);
        }
        return true;
    }
}
