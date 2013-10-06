package com.jim.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 8/27/13
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFileSocketServer extends AbstractSocketServer {
    @Override
    protected void sendObject(OutputStream outputStream, File file) throws Exception {
        sendFileName(outputStream, file);
        FileInputStream inputStream = new FileInputStream(file);
        addStream(inputStream);
        sendFile(outputStream, inputStream);
    }

    protected abstract void sendFileName(OutputStream outputStream, File file) throws Exception;

    protected abstract void sendFile(OutputStream outputStream, InputStream inputStream) throws Exception;
}
