package com.jim.util.encrypt;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 7/9/13
 * Time: 2:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestMyEncodeUtil {
    protected static Logger logger = Logger.getLogger(TestMyEncodeUtil.class);
    private static final String keyFile = "D:\\WebReach\\trunk\\common\\resources\\security\\key.dat";

    private String getIDString(String encodedStr) throws Exception {
        Key key = MyEncrypter.getKeyFromSavedFile(keyFile);
        byte[] decode = MyEncodeUtil.decode(encodedStr);
        String decrypt = MyEncrypter.decrypt(decode, key);
        return decrypt;
    }

    private String getKeyString(String idStr) throws Exception {
        Key key = MyEncrypter.getKeyFromSavedFile(keyFile);
        byte[] bytes = MyEncrypter.encrypt(String.valueOf(idStr), key);
        String uKey = MyEncodeUtil.encode(bytes);
        return uKey;
    }

    @Test
    public void testGetIDString() throws Exception {
        List<String> params = new ArrayList<String>();
        params.add("39C-89W-5H38C-15R2A125E56W");
        for (String code : params) {
            String idString = getIDString(code);
            System.out.println("idString = " + idString);
        }
    }

    @Test
    public void testGetKeyString() throws Exception {
        List<String> params = new ArrayList<String>();
        params.add("37420");
        params.add("29107");
        for (String id : params) {
            String keyString = getKeyString(id);
            System.out.println("keyString = " + keyString);
        }
    }

    @Test
    public void testIDEqualsKey() throws Exception {
        List<String> params = new ArrayList<String>();
        params.add("37420");
        for (String id : params) {
            String keyString = getKeyString(id);
            String idString = getIDString(keyString);
            assertEquals(id, idString);
        }
    }
}
