package com.jim.util.encrypt;

/**
 * Created by IntelliJ IDEA.
 * User: gur01
 * Date: Feb 3, 2009
 * Time: 1:58:00 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;


public class MyEncrypter {

    private static String algorithm = "DESede";

    private static Cipher cipher = null;

    public static void setUp() throws Exception {
        cipher = Cipher.getInstance(algorithm);
    }

    public static void generateKeyFile(Key key, String savedFile) throws Exception {
        FileOutputStream f = new FileOutputStream(savedFile);
        ObjectOutputStream so = new ObjectOutputStream(f);
        so.writeObject(key);
        so.flush();
    }

    public static Key getKeyFromSavedFile(String savedFile) throws Exception {
        FileInputStream in = new FileInputStream(savedFile);
        ObjectInputStream si = new ObjectInputStream(in);
        return (Key) si.readObject();
    }

    public synchronized static byte[] encrypt(String input, Key key)
            throws Exception {
        if (cipher == null) {
            setUp();
        }
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = input.getBytes();
        return cipher.doFinal(inputBytes);
    }

    public synchronized static String decrypt(byte[] encryptionBytes, Key key) throws Exception {
        if (cipher == null) {
            setUp();
        }
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
        String recovered = new String(recoveredBytes);
        return recovered;
    }

}
