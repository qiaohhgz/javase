package com.jim.mail;

import com.jim.util.StringUtil;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/22/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class JMailDefault implements JMail {
    protected static Logger logger = Logger.getLogger(JMailDefault.class);
    private String smtphost;
    private boolean debug;
    private String debugEmail;
    private String userName;
    private String passWord;

    public JMailDefault(String host, boolean debug) {
        this.smtphost = host;
        this.debug = debug;
    }

    public JMailDefault(String host, boolean debug, String userName, String passWord) {
        this.smtphost = host;
        this.debug = debug;
        this.userName = userName;
        this.passWord = passWord;
    }

    static class SimpleAuthenticator extends Authenticator {
        private String user;
        private String pass;

        public SimpleAuthenticator(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pass);
        }
    }


    /**
     * Create a String array with emails by parsing each of the string in the array
     * that is passed in for delimiters semi-colon, comma and space.
     *
     * @param recipients
     * @return
     */
    public String[] createNewEmailArrayByHandlingDelimiters(String[] recipients) {
        if (recipients != null && recipients.length > 0) {
            String strEmailAddress = StringUtil.arrayToCommaSeperatedString(recipients);
            strEmailAddress = StringUtil.getNewDelimitedEmailStr(strEmailAddress, "[,;\\s]", ",");
            if (strEmailAddress != null) {
                recipients = strEmailAddress.split(",");
            }
        }
        return recipients;
    }

    /**
     * Create a String array with emails by parsing string
     * that is passed in for delimiters semi-colon, comma and space.
     *
     * @param recipients
     * @return
     */
    public String[] createNewEmailArrayByHandlingDelimiters(String recipients) {
        String[] retEmailArray = null;
        if (recipients != null && recipients.trim().length() > 0) {
            recipients = StringUtil.getNewDelimitedEmailStr(recipients, "[,;\\s]", ",");
            if (recipients != null) {
                retEmailArray = recipients.split(",");
            }
        }
        return retEmailArray;
    }

    public void postMail(String recipients[], String subject, String message, String from) throws MessagingException {
        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.debug", "true");

        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);
        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "]  From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);

        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
        }

        // Optional : You can also set your custom headers in the Email if you Want
        msg.addHeader("MyHeaderName", "myHeaderValue");

        // Setting the Subject and Content Type
        msg.setContent(message, "text/html; charset=UTF-8");
        Transport.send(msg);
    }

    public void send(String recipients[], String ccRecipients[], String bccRecipients[], String subject, String message, String from) throws MessagingException {
        logger.debug("IsDebug:" + debug);
        if (debug) {
            logger.debug("Subject:" + subject);
            logger.debug("Message:" + subject);
            logger.debug("Recipients:" + toString(recipients));
            logger.debug("ccRecipients:" + toString(ccRecipients));
            logger.debug("bccRecipients:" + toString(bccRecipients));
            logger.debug("From:" + from);
            logger.debug("smtphost:" + smtphost);
            logger.debug("Debug Email:" + debugEmail);
        }

        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        ccRecipients = createNewEmailArrayByHandlingDelimiters(ccRecipients);
        bccRecipients = createNewEmailArrayByHandlingDelimiters(bccRecipients);

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        Message msg = new MimeMessage(session);

        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        msg.addHeader("MyHeaderName", "myHeaderValue");

        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] Cc:[" + toString(ccRecipients) +
                    "] BCC:[" + toString(bccRecipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);
        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            InternetAddress[] ccAddress = new InternetAddress[ccRecipients.length];
            for (int i = 0; i < ccRecipients.length; i++) {
                ccAddress[i] = new InternetAddress(ccRecipients[i]);
            }
            InternetAddress[] bccAddress = new InternetAddress[bccRecipients.length];
            for (int i = 0; i < bccRecipients.length; i++) {
                bccAddress[i] = new InternetAddress(bccRecipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setRecipients(Message.RecipientType.CC, ccAddress);
            msg.setRecipients(Message.RecipientType.BCC, bccAddress);

            msg.setSubject(subject);
        }
        msg.setContent(message, "text/html; charset=UTF-8");
        logger.debug("Full Message:" + msg.toString());
        Transport.send(msg);
    }

    public void send(String recipients[], String ccRecipients[], String subject, String message, String from) throws MessagingException {
        logger.debug("IsDebug:" + debug);
        if (debug) {
            logger.debug("Subject:" + subject);
            logger.debug("Message:" + subject);
            logger.debug("Recipients:" + toString(recipients));
            logger.debug("ccRecipients:" + toString(ccRecipients));
            logger.debug("From:" + from);
            logger.debug("smtphost:" + smtphost);
            logger.debug("Debug Email:" + debugEmail);
        }

        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        ccRecipients = createNewEmailArrayByHandlingDelimiters(ccRecipients);

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        Message msg = new MimeMessage(session);

        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        msg.addHeader("MyHeaderName", "myHeaderValue");

        if (debug) {
            // String[] debugEmails = debugEmail.split(",");
            String[] debugEmails = new String[]{"wanghaichen@hyron.com"};
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] Cc:[" + toString(ccRecipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);
        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            if (ccRecipients != null && ccRecipients.length > 0) {
                InternetAddress[] ccAddress = new InternetAddress[ccRecipients.length];
                for (int i = 0; i < ccRecipients.length; i++) {
                    ccAddress[i] = new InternetAddress(ccRecipients[i]);
                }
                msg.setRecipients(Message.RecipientType.CC, ccAddress);
            }
            msg.setSubject(subject);
        }
//        msg.setContent(message, "text/html");
        msg.setContent(message, "text/html; charset=UTF-8");
        logger.debug("Full Message:" + msg.toString());
        Transport.send(msg);
    }


    public void send(String recipients[], String subject, String message, String from, boolean overrideDebug) throws MessagingException {
        logger.debug("IsDebug:" + overrideDebug);
        if (overrideDebug) {
            logger.debug("Subject:" + subject);
            logger.debug("Message:" + subject);
            logger.debug("Recipients:" + toString(recipients));
            logger.debug("From:" + from);
            logger.debug("smtphost:" + smtphost);
            logger.debug("Debug Email:" + toString(recipients));
        }

        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(overrideDebug);

        Message msg = new MimeMessage(session);

        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        msg.addHeader("MyHeaderName", "myHeaderValue");

        if (overrideDebug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);
        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
        }
//        msg.setContent(message, "text/html");
        msg.setContent(message, "text/html; charset=UTF-8");
        logger.debug("Full Message:" + msg.toString());
        Transport.send(msg);
    }

    public void postMail(String recipients[], String subject, String message, String from, String attachment) throws Exception {
        postMail(recipients, subject, message, from, attachment, debug);
    }

    public void postMail(String recipients[], String[] ccRecipients, String subject, String message, String from,
                         String nameofTheFileToBeUsedInEmail, String attachmentContent) throws Exception {
        logger.debug("IsDebug:" + debug);
        if (debug) {
            logger.debug("Subject:" + subject);
            logger.debug("Message:" + subject);
            logger.debug("Recipients:" + toString(recipients));
            logger.debug("ccRecipients:" + toString(ccRecipients));
            logger.debug("From:" + from);
            logger.debug("smtphost:" + smtphost);
            logger.debug("Debug Email:" + debugEmail);
        }

        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        ccRecipients = createNewEmailArrayByHandlingDelimiters(ccRecipients);

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        //props.put("mail.smtp.host", "smtp-kop.corp.ybusa.net");
        props.put("mail.debug", "true");

        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] " +
                    "CC:[" + toString(ccRecipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);
        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            if (ccRecipients != null && ccRecipients.length > 0) {
                InternetAddress[] ccAddress = new InternetAddress[ccRecipients.length];
                for (int i = 0; i < ccRecipients.length; i++) {
                    ccAddress[i] = new InternetAddress(ccRecipients[i]);
                }
                msg.setRecipients(Message.RecipientType.CC, ccAddress);
            }
            msg.setSubject(subject);
        }


        // create the message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");

        //Create Multipart
        Multipart multipart = new MimeMultipart();
        //fill message
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        if (attachmentContent != null && attachmentContent.trim().length() > 0) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.setDataHandler(new DataHandler(attachmentContent, "text/html"));
            attachmentBodyPart.setFileName(nameofTheFileToBeUsedInEmail);
            multipart.addBodyPart(attachmentBodyPart);
        }

        // Put parts in message
        msg.setContent(multipart);

        // Send the message
        Transport.send(msg);
    }

    public void postMail(String recipients[], String subject, String message, String from, String filePathAndName, String nameofTheFileToBeUsedInEmail, boolean overridedebug) throws Exception {
        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        //props.put("mail.smtp.host", "smtp-kop.corp.ybusa.net");
        props.put("mail.debug", "true");

        // create some properties and get the default Session
        //
        //
        // Session session = Session.getDefaultInstance(props, null);(yuanlaidedaima)
        //
        //test start
        Session session = null;
        if (userName != null && passWord != null && !userName.trim().equals("") && !passWord.trim().equals("")) {
            props.put("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(props, new SimpleAuthenticator(userName, passWord));
        } else {
            session = Session.getDefaultInstance(props, null);
        }
        //test end

//        session.setDebug(overridedebug);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        if (debug) {
//            String[] debugEmails = debugEmail.split(",");
            String[] debugEmails = new String[]{"qiaoshiju@hyron.com"};
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);

        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
        }


        // create the message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");

        //Create Multipart
        Multipart multipart = new MimeMultipart();
        //fill message
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePathAndName);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(nameofTheFileToBeUsedInEmail);
        multipart.addBodyPart(attachmentBodyPart);

        // Put parts in message
        msg.setContent(multipart);

        // Send the message
        Transport.send(msg);

    }

    public void postMail(String recipients[], String subject, String message, String from, String filePathAndName, String nameofTheFileToBeUsedInEmail) throws Exception {
        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        //props.put("mail.smtp.host", "smtp-kop.corp.ybusa.net");
        props.put("mail.debug", "true");

        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);

        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
        }


        // create the message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");

        //Create Multipart
        Multipart multipart = new MimeMultipart();
        //fill message
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePathAndName);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(nameofTheFileToBeUsedInEmail);
        multipart.addBodyPart(attachmentBodyPart);

        // Put parts in message
        msg.setContent(multipart);

        // Send the message
        Transport.send(msg);

    }

    public void postMail(String recipients[], String subject, String message, String from, String attachment, boolean overridedebug) throws Exception {
        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }
        System.getProperties().setProperty("http.proxyHost", "172.20.230.5");
        System.getProperties().setProperty("http.proxyPort", "3128");
        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        //props.put("mail.smtp.host", "smtp-kop.corp.ybusa.net");
        props.put("mail.debug", "true");
        Session session = null;
        if (userName != null && passWord != null && !userName.trim().equals("") && !passWord.trim().equals("")) {
            props.put("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(props, new SimpleAuthenticator(userName, passWord));
        } else {
            session = Session.getDefaultInstance(props, null);
        }
        // create some properties and get the default Session
        //Session session = Session.getDefaultInstance(props, null);
        session.setDebug(overridedebug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        if (overridedebug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);

        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
        }


        // create the message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");

        //Create Multipart
        Multipart multipart = new MimeMultipart();
        //fill message
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);
        attachmentBodyPart.setDataHandler(new DataHandler(source));

//        attachmentBodyPart.setFileName(FileUtil.getNameFromPath(attachment));
        multipart.addBodyPart(attachmentBodyPart);

        // Put parts in message
        msg.setContent(multipart);

        // Send the message
        Transport.send(msg);

    }

    public void postMail(String recipients[], String ccRecipients[], String subject, String message, String from, String attachment) throws Exception {
        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }
        System.getProperties().setProperty("http.proxyHost", "172.20.230.5");
        System.getProperties().setProperty("http.proxyPort", "3128");

        ccRecipients = createNewEmailArrayByHandlingDelimiters(ccRecipients);

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        //props.put("mail.smtp.host", "smtp-kop.corp.ybusa.net");
        props.put("mail.debug", "true");
        Session session = null;
        if (userName != null && passWord != null && !userName.trim().equals("") && !passWord.trim().equals("")) {
            props.put("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(props, new SimpleAuthenticator(userName, passWord));
        } else {
            session = Session.getDefaultInstance(props, null);
        }
        // create some properties and get the default Session
        //Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);


        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] Cc:[" + toString(ccRecipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);

        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            InternetAddress[] ccAddress = new InternetAddress[ccRecipients.length];
            for (int i = 0; i < ccRecipients.length; i++) {
                ccAddress[i] = new InternetAddress(ccRecipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setRecipients(Message.RecipientType.CC, ccAddress);
            msg.setSubject(subject);
        }

        // create the message part
        MimeBodyPart messageBodyPart =
                new MimeBodyPart();

        //fill message
        //messageBodyPart.setText(message);
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        DataSource source =
                new FileDataSource(attachment);
        messageBodyPart.setDataHandler(
                new DataHandler(source));
        File f = new File(attachment);
        messageBodyPart.setFileName(f.getName());
        multipart.addBodyPart(messageBodyPart);

        // Put parts in message
        msg.setContent(multipart);

        // Send the message
        Transport.send(msg);

    }

    public void postMail(String recipients[], String ccRecipients[], String bccRecipients[], String subject, String message, String from, String attachment) throws Exception {
        recipients = createNewEmailArrayByHandlingDelimiters(recipients);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        ccRecipients = createNewEmailArrayByHandlingDelimiters(ccRecipients);
        bccRecipients = createNewEmailArrayByHandlingDelimiters(bccRecipients);

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        //props.put("mail.smtp.host", "smtp-kop.corp.ybusa.net");
        props.put("mail.debug", "true");

        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);


        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "] Cc:[" + toString(ccRecipients) + "] Bcc:[" + toString(bccRecipients) + "] From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);

        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            InternetAddress[] ccAddress = new InternetAddress[ccRecipients.length];
            for (int i = 0; i < ccRecipients.length; i++) {
                ccAddress[i] = new InternetAddress(ccRecipients[i]);
            }
            InternetAddress[] bccAddress = new InternetAddress[bccRecipients.length];
            for (int i = 0; i < bccRecipients.length; i++) {
                bccAddress[i] = new InternetAddress(bccRecipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setRecipients(Message.RecipientType.CC, ccAddress);
            msg.setRecipients(Message.RecipientType.BCC, bccAddress);
            msg.setSubject(subject);
        }

        // create the message part
        MimeBodyPart messageBodyPart =
                new MimeBodyPart();

        //fill message
        //messageBodyPart.setText(message);
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        DataSource source =
                new FileDataSource(attachment);
        messageBodyPart.setDataHandler(
                new DataHandler(source));
        File f = new File(attachment);
        messageBodyPart.setFileName(f.getName());
        multipart.addBodyPart(messageBodyPart);


        // Put parts in message
        msg.setContent(multipart);

        // Send the message
        Transport.send(msg);

    }


    /**
     * post email without attachment file
     *
     * @param recipientStr
     * @param subject
     * @param message
     * @param from
     * @return void
     * @throws MessagingException
     * @author Frank
     * @see JMailDefault
     */
    public void postMail(String recipientStr, String subject, String message, String from) throws MessagingException {
        String[] recipients = createNewEmailArrayByHandlingDelimiters(recipientStr);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        /* Set the host smtp address */
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.debug", debug);

        /* create some properties and get the default Session */
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        /* create a message */
        Message msg = new MimeMessage(session);
        /* set the from and to address */
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "]  From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);
        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
        }

        /*
           * Optional : You can also set your custom headers in the Email if you
           * Want
           */
        msg.addHeader("MyHeaderName", "myHeaderValue");

        /* Setting the Subject and Content Type */
        msg.setSubject(subject);
        msg.setContent(message, "text/html; charset=UTF-8");
        Transport.send(msg);
    }

    /**
     * post email with attachment file
     *
     * @param recipientStr
     * @param subject
     * @param message
     * @param from
     * @param attachment
     * @return void
     * @throws Exception
     * @author Frank
     * @see JMailDefault
     */
    public void postMail(String recipientStr, String subject, String message, String from, String attachment) throws Exception {
        String[] recipients = createNewEmailArrayByHandlingDelimiters(recipientStr);
        if (recipients == null || recipients.length == 0) {
            throw new MessagingException("Unable to send email as there are no recipients mentioned.", null);
        }

        /* Set the host smtp address */
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.debug", "true");

        /* create some properties and get the default Session */
        //
        //
        // Session session = Session.getDefaultInstance(props, null);(yuanlaidedaima)
        //
        //test start
        Session session = null;
        if (userName != null && passWord != null && !userName.trim().equals("") && !passWord.trim().equals("")) {
            props.put("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(props, new SimpleAuthenticator(userName, passWord));
        } else {
            session = Session.getDefaultInstance(props, null);
        }
        //test end

        session.setDebug(debug);

        /* create a message */
        Message msg = new MimeMessage(session);

        /* set the from and to address */
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        if (debug) {
            String[] debugEmails = debugEmail.split(",");
            InternetAddress[] debugTo = new InternetAddress[debugEmails.length];
            for (int i = 0; i < debugEmails.length; i++) {
                debugTo[i] = new InternetAddress(debugEmails[i]);
            }
            String debugSubject = "Debug Mode: Subject:[" + subject + "] To:[" + toString(recipients) + "]  From:[" + from + "]";
            msg.setRecipients(Message.RecipientType.TO, debugTo);
            msg.setSubject(debugSubject);

        } else {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
        }

        /* create the message part */
        MimeBodyPart messageBodyPart = new MimeBodyPart();

        /* fill message */
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        /* Part two is attachment */
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);
        messageBodyPart.setDataHandler(new DataHandler(source));
        attachment = attachment.substring(attachment.lastIndexOf("\\") + 1);
        messageBodyPart.setFileName(attachment);
        multipart.addBodyPart(messageBodyPart);

        /* Put parts in message */
        msg.setContent(multipart);

        /* Send the message */
        Transport.send(msg);
    }

    private String toString(String[] argrs) {
        if (argrs == null)
            return null;
        String ret = "";
        for (String str : argrs) {
            if (!"".equals(ret))
                ret += ",";
            ret += str;
        }
        return ret;
    }


    public String getSmtphost() {
        return smtphost;
    }

    public void setSmtphost(String smtphost) {
        this.smtphost = smtphost;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getDebugEmail() {
        return debugEmail;
    }

    public void setDebugEmail(String debugEmail) {
        this.debugEmail = debugEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
