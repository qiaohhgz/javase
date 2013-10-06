package com.jim.mail;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/22/13
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestJMailDefault {
    @Test
    @Ignore
    public void testPost() throws Exception {
        System.out.println("Testing Post Method");
        JMailDefault mail = new JMailDefault("smtp.163.com", false, "qsjhhgz@163.com", "q7837793");
        mail.postMail(new String[]{"qiaohhgz@163.com"}, "subject", "hello", "qsjhhgz@163.com");
    }
}
