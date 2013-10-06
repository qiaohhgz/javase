package com.jim.designpattern.abstractfactory.v1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/31/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonFactory implements BeanFactory {
    private Map<String, Object> container = new HashMap<String, Object>();

    public JsonFactory(String fileName) {

    }

    @Override
    public Object getBean(String id) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
