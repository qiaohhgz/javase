package com.jim.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("qsjtest@outlook.com", "q123456789");
	}
}
