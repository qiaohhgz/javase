package com.jim.util.encrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cheng01
 * Date: Mar 20, 2009
 * Time: 5:17:28 PM
 * Copyright 2008, Gang Chen
 */
public class MyEncodeUtil {
    private static final String[] separators = new String[]{"W", "E", "B", "R", "A", "C", "H"};

    public static String encode(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String singlebyte = new Integer(bytes[i]).toString();
            sb.append(singlebyte);
            int index = (int) (Math.random() * 7.0);
            sb.append(separators[index]);
        }
        return sb.toString();
    }


    public static byte[] decode(String encodedStr) {
        String[] byteStrs = split(encodedStr);
        byte[] bytes = new byte[byteStrs.length];
        for (int i = 0; i < byteStrs.length; i++) {
            bytes[i] = new Byte(byteStrs[i]);
        }
        return bytes;
    }

    private static String[] split(String encodeStr) {
        List<String> sl = new ArrayList<String>();
        while (true) {
            int index = getNextIndex(encodeStr);
            if (index == -1) break;
            sl.add(encodeStr.substring(0, index));
            encodeStr = encodeStr.substring(index + 1);
        }
        return sl.toArray(new String[0]);
    }

    private static int getNextIndex(String encodeStr) {
        int index = encodeStr.length();
        for (String sep : separators) {
            int newIndex = encodeStr.indexOf(sep);
            if (newIndex != -1 && newIndex < index) index = newIndex;
        }
        return index == encodeStr.length() ? -1 : index;
    }
}
