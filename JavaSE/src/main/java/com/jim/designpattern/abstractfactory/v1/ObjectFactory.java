package com.jim.designpattern.abstractfactory.v1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/14/13
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectFactory implements BeanFactory {
    private Map<String, Object> container = new HashMap<String, Object>();

    @Override
    public Object getBean(String id) {
        return container.get(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ObjectFactory(final String filePath) {
        File file = new File(filePath);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            container.put(file.getName(), o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
