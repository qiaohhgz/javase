package com.jim.util.property;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/25/13
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SortedProperties extends Properties {
    /**
     * Overrides, called by the store method.
     */
    @SuppressWarnings("unchecked")
    public synchronized Enumeration keys() {
        Enumeration keysEnum = super.keys();
        Vector keyList = new Vector();
        while (keysEnum.hasMoreElements()) {
            keyList.add(keysEnum.nextElement());
        }
        Collections.sort(keyList);
        return keyList.elements();
    }
}