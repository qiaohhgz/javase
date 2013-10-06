package com.jim.util.print;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Printable {
    String appendNotation(String message);

    void printNewLine(int lines);

    void print(String msg);

    PrintStream getOut();

    void setOut(PrintStream out);

    void printList(List list);

    void printMap(Map map);
}
