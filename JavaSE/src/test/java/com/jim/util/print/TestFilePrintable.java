package com.jim.util.print;

import org.junit.After;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestFilePrintable extends TestPrintable {
    private FilePrintable filePrintable;

    @Override
    public Printable getPrintable() {
        File file = null;
        try {
            file = File.createTempFile("javase.log", "");
        } catch (IOException e) {
            return null;
        }
        filePrintable = new FilePrintable(file);
        return filePrintable;
    }

    @After
    public void close() throws Exception {
        filePrintable.close();
    }
}
