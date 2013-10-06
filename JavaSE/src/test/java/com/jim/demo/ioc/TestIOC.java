package com.jim.demo.ioc;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/16/13
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestIOC {
    @Test
    public void testMain() throws Exception {
        Config cfg = new Config();
        PersonFacade facade = (PersonFacade) cfg.getBean("PersonFacade");
        facade.save("Tom");
    }
}
