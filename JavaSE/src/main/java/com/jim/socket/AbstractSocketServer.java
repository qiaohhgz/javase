package com.jim.socket;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 8/26/13
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSocketServer extends AbstractCloseable {
    protected static final String SERVER_HOST = "172.20.230.58";
    protected static final int SERVER_PORT = 6666;

    public final void send(File obj) throws Exception {
        System.out.println("connecting ...");
        Socket socket = null;
        OutputStream outputStream;
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            boolean connected = socket.isConnected();
            if (connected) {
                System.out.println("is connected");
                outputStream = socket.getOutputStream();
                addStream(outputStream);

                sendObject(outputStream, obj);

                System.out.println("send successful");
            }
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("close socket");
                }
                close();
            } catch (IOException e) {

            }
        }
    }

    protected abstract void sendObject(OutputStream outputStream, File object) throws Exception;
}
