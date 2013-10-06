package com.jim.socket;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 8/26/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestClient {
    @Test
    @Ignore
    public void testSend() throws Exception {
        new FileSocketServer().send(new File("E:\\software\\Unlocker1.9.1-x64.exe"));
    }

    @Test
    @Ignore
    public void testConnect() throws Exception {
        Socket socket = new Socket("172.20.230.58", 6666);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.close();
    }
}
