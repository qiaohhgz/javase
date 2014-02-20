package com.jim;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 2/12/14
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestDateFormat {
    @Test
    public void testName() throws Exception {
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale locale : availableLocales) {
            showInfo(locale);
        }
    }

    public void showInfo(Locale locale) {
        DateStyleAdapter[] dateStyleArray = DateStyleAdapter.values();
        for (DateStyleAdapter dateStyleAdapter : dateStyleArray) {
            DateFormat format = DateFormat.getDateInstance(dateStyleAdapter.getStyle(), locale);
            System.out.printf("%s => %s(%s) \n", locale.getDisplayName(), format.format(new Date()), dateStyleAdapter.name());
        }
        System.out.println();
    }

    public enum DateStyleAdapter {
        FULL(DateFormat.FULL),
        LONG(DateFormat.LONG),
        MEDIUM(DateFormat.MEDIUM),
        SHORT(DateFormat.SHORT),
        DEFAULT(DateFormat.DEFAULT);

        int style;

        DateStyleAdapter(int style) {
            this.style = style;
        }

        public int getStyle() {
            return style;
        }
    }
}
