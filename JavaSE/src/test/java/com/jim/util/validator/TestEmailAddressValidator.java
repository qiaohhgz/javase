package com.jim.util.validator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/30/13
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestEmailAddressValidator {
    private static final String VALID_EMAIL_JSON_FILE_NAME = "emails.json";
    private JsonArray myValidEmail;
    private String[] myInvalidEmail = new String[]{
            "aaaaaaaa", "", "null", "@yahoo", "adam@debois@yahoo.com", "adam.debois#yahoo.com","adam@debois@yahoo.com@"
    };

    @Test
    public void testValidateEmailAddress() throws Exception {
        String email;
        for (int i = 0; i < myInvalidEmail.length; i++) {
            email = myInvalidEmail[i];
            printEmail(email, true);
            Assert.assertFalse(EmailAddressValidator.validateEmailAddress(myInvalidEmail[i]));
        }
        for (int i = 0; i < myValidEmail.size(); i++) {
            email = getEmailByJsonArray(myValidEmail, i);
            printEmail(email, false);
            Assert.assertTrue(EmailAddressValidator.validateEmailAddress(email));
        }
    }

    @Test
    public void testValidateEmailFormat() throws Exception {
        String email;
        for (int i = 0; i < myInvalidEmail.length; i++) {
            email = myInvalidEmail[i];
            printEmail(email, true);
            Assert.assertFalse(EmailAddressValidator.validateEmailFormat(email));
        }
        for (int i = 0; i < myValidEmail.size(); i++) {
            email = getEmailByJsonArray(myValidEmail, i);
            printEmail(email, false);
            Assert.assertTrue(EmailAddressValidator.validateEmailFormat(email));
        }
    }

    private void printEmail(String email, boolean invalid) {
        String msgPart = invalid ? "invalid" : "valid";
        System.out.println(msgPart + " email = " + email);
    }

    private void initValidEmailData() throws Exception {
        InputStream in = TestEmailAddressValidator.class.getClassLoader().getResourceAsStream(VALID_EMAIL_JSON_FILE_NAME);
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inr);
        JsonElement parse = new JsonParser().parse(reader);
        JsonArray items = parse.getAsJsonObject().get("items").getAsJsonArray();
        this.myValidEmail = items;
    }

    private String getEmailByJsonArray(JsonArray items, int index) {
        JsonElement email = items.get(index).getAsJsonObject().get("email");
        return email.getAsString();
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("**********     Init valid email data     **********");
        initValidEmailData();
    }
}
