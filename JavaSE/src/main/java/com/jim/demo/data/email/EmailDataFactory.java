package com.jim.demo.data.email;

import com.jim.demo.data.DataFactory;
import com.jim.demo.data.DemoData;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 9:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailDataFactory extends DataFactory {
    @Override
    public DemoData getDemoData() {
        return new EmailData();
    }
}
