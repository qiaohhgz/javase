package com.jim.demo.treemap;

import com.jim.demo.data.MyControl;
import com.jim.demo.data.email.EmailDataFactory;
import com.jim.util.print.ConsolePrintable;
import com.jim.util.print.Printable;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestTreeMap {
    public static void main(String[] args) {
        Printable print = new ConsolePrintable();

        Map<String, String> demoData = new MyControl(new EmailDataFactory()).getData();
        TreeMap<String, String> tree = new TreeMap(demoData);
        print.printMap(tree);
    }
}
