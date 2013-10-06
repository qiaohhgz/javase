package com.jim.util.validator;

import org.apache.commons.validator.EmailValidator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/30/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailAddressValidator {
    public static boolean validateEmailAddress(String emailAddress) {
        boolean valid = true;
        InternetAddress ua = null;
        try {
            ua = new InternetAddress(emailAddress);
            ua.validate();
        } catch (AddressException e) {
            valid = false;
        }

        return valid;
    }

    public static boolean validateEmailFormat(String emailAddress) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(emailAddress);
    }
}
