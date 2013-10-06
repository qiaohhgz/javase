package com.jim.designpattern.abstractfactory.v1;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/31/13
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassPathXmlApplicationContext {


    @Test
    public void testName() throws Exception {
        BeanFactory factory = new ClassPathXmlApplicationContext("bean.xml");
        Say student = (Say) factory.getBean("s");
        Say teacher = (Say) factory.getBean("t");
        student.say();
        teacher.say();
    }
}
