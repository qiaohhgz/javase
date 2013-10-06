package com.jim.demo.data;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyControl {
    private DataFactory factory;
    private DemoData demoData;

    public MyControl(DataFactory factory) {
        this.factory = factory;
    }

    public Map getData() {
        this.demoData = getFactory().getDemoData();
        return demoData.getData();
    }

    public DataFactory getFactory() {
        return factory;
    }

    public void setFactory(DataFactory factory) {
        this.factory = factory;
    }

    public DemoData getDemoData() {
        return demoData;
    }

    public void setDemoData(DemoData demoData) {
        this.demoData = demoData;
    }
}
