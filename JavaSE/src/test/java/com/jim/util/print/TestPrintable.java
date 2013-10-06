package com.jim.util.print;

import com.jim.demo.data.DataFactory;
import com.jim.demo.data.email.EmailDataFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TestPrintable {
    @Test
    public void testAppendNotation() throws Exception {
        getPrintable().appendNotation(null);
        getPrintable().appendNotation("Test");
    }

    @Test
    public void testPrintNewLine() throws Exception {
        getPrintable().printNewLine(1);
    }

    @Test
    public void testPrint() throws Exception {
        getPrintable().print(null);
        getPrintable().print("Test");
    }

    @Test
    public void testGetOut() throws Exception {
        getPrintable().getOut();
    }

    @Test
    public void testSetOut() throws Exception {
        getPrintable().setOut(null);
        getPrintable().setOut(System.out);
    }

    @Test
    public void testPrintList() throws Exception {
        DataFactory factory = new EmailDataFactory();
        getPrintable().printList(null);
        getPrintable().printList(new ArrayList(factory.getDemoData().getData().values()));
    }

    @Test
    public void testPrintMap() throws Exception {
        DataFactory factory = new EmailDataFactory();
        getPrintable().printMap(null);
        getPrintable().printMap(new HashMap(factory.getDemoData().getData()));
    }

    public abstract Printable getPrintable();
}
