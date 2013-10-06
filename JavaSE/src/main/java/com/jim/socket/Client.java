package com.jim.socket;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 8/27/13
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {
        FileSocketServer client = new FileSocketServer();
        try {
            File file = new File(args[0]);
            client.send(file);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
