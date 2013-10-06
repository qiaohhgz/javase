package com.jim.util.encrypt;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 7/10/13
 * Time: 1:49 AM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.log4j.Logger;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.jim.util.encrypt.MyEncodeUtil.decode;
import static com.jim.util.encrypt.MyEncrypter.*;

public class Main extends Thread {
    private static final Logger log = Logger.getLogger(Main.class);
    public static final String keyFile = "D:\\WebReach\\trunk\\common\\resources\\security\\key.dat";

    public static void main(String[] args) {
        List<Main> threads = new ArrayList<Main>();
        for (int i = 0; i < 100; i++) {
            Main t = new Main();
            threads.add(t);
            t.start();
        }
        while (true) {
            boolean allCompleted = true;
            for (Main thread : threads) {
                if (!thread.isCompleted) {
                    allCompleted = false;
                    break;
                }
            }
            if (allCompleted) {
                break;
            }
        }
        log.info("=================");
        for (Main thread : threads) {
            thread.check();
        }
    }

    private int userId;
    private String uKey;
    private boolean isCompleted;

    @Override
    public void run() {
        try {
            userId = new Random().nextInt(5000) + 1000;

            Key key = getKeyFromSavedFile(keyFile);
            byte[] bytes = encrypt(String.valueOf(userId), key);
            uKey = MyEncodeUtil.encode(bytes);
        } catch (Exception ex) {
            log.error("Encode userId = " + userId + " " + ex.getMessage());
        }
        isCompleted = true;
    }

    public boolean check() {
        try {
            Key key = getKeyFromSavedFile(keyFile);
            byte[] encrypt = decode(uKey);
            String decrypt = decrypt(encrypt, key);
            if (Integer.valueOf(decrypt).intValue() == userId) {
                log.debug("Decode userId: " + userId + " uKey: " + uKey);
                return true;
            }
        } catch (Exception ex) {
            log.error("Decode userId = " + userId + "\tuKey = " + uKey + "\tError:" + ex.getMessage());
        }
        return false;
    }
}
