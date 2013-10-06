package com.jim.util.print;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConsolePrintable implements Printable {
    private static final PrintStream DEFAULT_OUT = System.out;
    private PrintStream out = DEFAULT_OUT;

    @Override
    public void printList(List list) {
        print(appendNotation("Begin printList List"));
        if (list == null) {
            print(appendNotation("List is empty"));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            print(String.format("List[%d] = %s", i, list.get(i).toString()));
        }
    }

    @Override
    public void printMap(Map map) {
        print(appendNotation("Begin printList Map"));
        if (map == null) {
            print(appendNotation("Map is empty"));
            return;
        }
        Set<String> keys = map.keySet();
        for (Object key : keys) {
            Object value = map.get(key);
            print(String.format("key = %s ; value = %s", key, value));
        }
    }

    @Override
    public String appendNotation(String message) {
        if (null == message) return message;
        return String.format("**********     %s     **********", message);
    }

    @Override
    public void printNewLine(int lines) {
        for (int i = 0; i < lines; i++) {
            print(null);
        }
    }

    @Override
    public void print(String msg) {
        if (null == msg) {
            getOut().println();
        } else {
            getOut().println(msg);
        }
    }

    @Override
    public PrintStream getOut() {
        return out;
    }

    @Override
    public void setOut(PrintStream out) {
        this.out = out;
    }
}
