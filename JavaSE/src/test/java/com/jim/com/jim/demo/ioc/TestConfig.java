package com.jim.com.jim.demo.ioc;

import com.jim.demo.ioc.Config;
import com.jim.demo.ioc.PersonFacade;
import org.dom4j.DocumentFactory;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/22/13
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestConfig {
    @Test
    public void testName() throws Exception {
        Config cfg = new Config();
        PersonFacade facade = (PersonFacade) cfg.getBean("PersonFacade");
        facade.save("Tom");

        DocumentFactory factory = DocumentFactory.getInstance();
        factory.createDocument();
    }
}
