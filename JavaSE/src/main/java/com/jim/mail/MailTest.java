package com.jim.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class MailTest {
//    public static final String from = "webreach-smtp-relay@yellowbook.com";
    public static final String from = "qiaohhgz@163.com";
    public static final String to = "qiaoshiju@hyron.com";
	public static void main(String[] args) throws Exception {
		Properties pro = System.getProperties();
		
		System.getProperties().setProperty("http.proxyHost", "172.20.230.5");
        System.getProperties().setProperty("http.proxyPort", "3128");

//		pro.put("proxySet", true);
//		pro.put("mail.smtp.host", "210.22.128.203");
//		pro.put("mail.smtp.port", 25);
//		pro.put("mail.smtp.auth", false);
//		pro.put("mail.debug", true);

        pro.put("mail.smtp.host", "stmp.outlook.com");
        pro.put("mail.smtp.port", 25);
        pro.put("mail.smtp.auth", true);
        pro.put("mail.debug", true);

		Authenticator auth = new MyAuthenticator();

		Session session = Session.getDefaultInstance(pro, auth);
		
		session.setDebug(true);
		Message message = new MimeMessage(session);
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("收到请回复", "text/html; charset=UTF-8");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		message.setContent(multipart);
		
		message.setSubject("jim test subject");

		InternetAddress fromaddress = new InternetAddress(from);
		InternetAddress tomaddress = new InternetAddress(to);
		message.setFrom(fromaddress);
		message.addRecipient(Message.RecipientType.TO, tomaddress);
		message.setSentDate(new Date());
		message.saveChanges();
		System.out.println("sending ....");
		Transport.send(message);
		System.out.println("sended ....");
	}
}
