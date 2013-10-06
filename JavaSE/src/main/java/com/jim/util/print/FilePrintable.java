package com.jim.util.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class FilePrintable extends ConsolePrintable {
    private PrintStream out;

    public FilePrintable(File file) {
        initPrintStream(file);
        super.setOut(out);
    }

    private void initPrintStream(File file) {
        try {
            out = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (null != out) {
            out.flush();
            out.close();
        }
    }
}
