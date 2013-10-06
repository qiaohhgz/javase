package com.jim.demo.data.email;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jim.demo.data.DemoData;
import com.jim.util.print.ConsolePrintable;
import com.jim.util.print.Printable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/8/13
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailData implements DemoData {
    private static final String DEFAULT_FILE_NAME = "emails.json";
    private String fileName = DEFAULT_FILE_NAME;
    private Map data;
    private Printable print = new ConsolePrintable();

    @Override
    public Map getData() {
        try {
            initValidEmailData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    private String getEmailByJsonArray(JsonArray items, int index, String fieldName) {
        JsonElement email = items.get(index).getAsJsonObject().get(fieldName);
        return email == null ? null : email.getAsString();
    }

    private void initValidEmailData() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream(getFileName());
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inr);
        JsonElement parse = new JsonParser().parse(reader);
        JsonArray items = parse.getAsJsonObject().get("items").getAsJsonArray();

        data = getDefaultMap();
        for (int i = 0; i < items.size(); i++) {
            String lastName = getEmailByJsonArray(items, i, "last");
            String email = getEmailByJsonArray(items, i, "email");
            if (lastName == null || email == null) {
                print.print(String.format("lastName = %s ; email = %s ; i = %d", lastName, email, i));
            }
            data.put(lastName, email);
        }
    }

    public Map getDefaultMap() {
        return new HashMap();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
