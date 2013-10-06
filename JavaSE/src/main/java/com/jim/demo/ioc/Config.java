package com.jim.demo.ioc;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Config {
    private static final Logger log = Logger.getLogger(Config.class);
    private Map<String, Object> beans = new HashMap<String, Object>();

    public Config() {
        try {
            String facadeClassName = "com.jim.demo.ioc.PersonFacade";
            String daoClassName = "com.jim.demo.ioc.PersonDaoImpl";
            String interfaceClassName = "com.jim.demo.ioc.PersonDao";

            Class facadeClass = Class.forName(facadeClassName);
            Class daoClass = Class.forName(daoClassName);
            Class daoInterClass = Class.forName(interfaceClassName);

            Object personFacade = facadeClass.newInstance();
            Object personDaoImpl = daoClass.newInstance();

            Method method = facadeClass.getDeclaredMethod("setPersonDao", daoInterClass);
            method.invoke(personFacade, personDaoImpl);

            beans.put("PersonFacade", personFacade);
            beans.put("PersonDaoImpl", personDaoImpl);
        } catch (ClassNotFoundException e) {
            log.error(e);
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (NoSuchMethodException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }
}
