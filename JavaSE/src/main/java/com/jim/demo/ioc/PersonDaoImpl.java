package com.jim.demo.ioc;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonDaoImpl implements PersonDao {
    private static final Logger log = Logger.getLogger(PersonDaoImpl.class);

    @Override
    public void save(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
        log.info("save success name = " + name);
    }
}
