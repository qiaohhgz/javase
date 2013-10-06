package com.jim.designpattern.abstractfactory.v1;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/14/13
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestObjectFactory {
    @Test
    public void testGetBean() throws Exception {
        String filePath = getClass().getClassLoader().getResource("Animal/black_gold_carp.anm").getFile();
        BeanFactory factory = new ObjectFactory(filePath);
        Object bean = factory.getBean("black_gold_carp.anm");
        assert bean != null;
    }
}
