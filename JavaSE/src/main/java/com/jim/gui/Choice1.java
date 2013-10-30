package com.jim.gui;

import java.applet.Applet;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 10/21/13
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Choice1 extends Applet {
    String[] description = {"Ebullient", "Obtuse",
            "Recalcitrant", "Brilliant", "Somnescent",
            "Timorous", "Florid", "Putrescent"};
    TextField t = new TextField(30);
    Choice c = new Choice();
    Button b = new Button("Add items");
    int count = 0;

    @Override
    public void init() {
        t.setEditable(false);
        for (int i = 0; i < 4; i++) {
            c.addItem(description[count++]);
        }
        add(t);
        add(c);
        add(b);
    }

    @Override
    public boolean action(Event evt, Object what) {
        if (evt.target.equals(c))
            t.setText("index: " + c.getSelectedIndex()
                    + " " + (String) what);
        else if (evt.target.equals(b)) {
            if (count < description.length)
                c.addItem(description[count++]);
        } else
            return super.action(evt, what);
        return true;
    }

}
