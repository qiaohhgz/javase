package com.jim.demo.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SMTPAppender;

import java.io.FileNotFoundException;
public class Log4jSendMail {
    public static final Logger log = Logger.getLogger(Log4jSendMail.class);
    static Logger logger = Logger.getLogger(Log4jSendMail.class);
    SMTPAppender mail = new SMTPAppender();

    public Log4jSendMail() {
        try {
            String from = "chenbinbin@hyron.com";
            String to = "qiaoshiju@hyron.com";
            mail.setFrom(from);
            mail.setTo(to);
            mail.setSMTPHost("210.22.128.203");
            mail.setLocationInfo(true);
            mail.setSubject("Test Mail From Log4J");
            PatternLayout layout = new PatternLayout();
            mail.setLayout(layout);
            mail.activateOptions();
            logger.addAppender(mail);
            logger.error("Hello World");
            System.out.println("sended ....");
        } catch (Exception e) {
            logger.error("Printing ERROR Statements", e);
        }
    }

    public static void main(String args[]) {
        log.error(new FileNotFoundException());
    }
}
