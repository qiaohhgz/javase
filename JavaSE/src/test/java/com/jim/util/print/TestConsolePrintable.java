package com.jim.util.print;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestConsolePrintable extends TestPrintable {
    Printable printable = new ConsolePrintable();

    @Override
    public Printable getPrintable() {
        return printable;
    }
}
