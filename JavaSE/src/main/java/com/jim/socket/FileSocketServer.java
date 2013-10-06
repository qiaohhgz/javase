package com.jim.socket;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 8/27/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileSocketServer extends AbstractFileSocketServer {
    @Override
    protected void sendFileName(OutputStream outputStream, File file) throws Exception {
        String fileName = "Default";
        if (null != file && file.isFile()) {
            fileName = file.getName();
        }
        System.out.println("fileName = " + fileName);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        writer.write(fileName);
        writer.newLine();
        writer.flush();
    }

    @Override
    protected void sendFile(OutputStream outputStream, InputStream inputStream) throws Exception {
        byte[] bs = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bs)) != -1) {
            outputStream.write(bs, 0, len);
        }
    }
}
