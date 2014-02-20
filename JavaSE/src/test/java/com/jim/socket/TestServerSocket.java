package com.jim.socket;


import com.jim.net.udp.FileMonitor;
import com.jim.util.proxy.MyProxy;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/27/13
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestServerSocket {
    @Test
//    @Ignore
    public void testDoServer() throws Exception {
//        MyProxy.applyProxy();
        ServerSocket server = new ServerSocket(6666);
        Thread monitor;
        try {
            while (true) {
                System.out.println("start server");
                Socket client = server.accept();
                System.out.println("Connection a client ...");
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
                String fileName = reader.readLine();
                System.out.println("fileName = " + fileName);
                File file = new File("D:/MyDownload/" + fileName);
                monitor = new Thread(new FileMonitor(file, 50, 10204));
                monitor.start();
                FileUtils.copyInputStreamToFile(client.getInputStream(), file);
                monitor.stop();
                System.out.println("Disconnect client");
            }
        } catch (Exception ex) {

        } finally {
            if (server != null) {
                System.out.println("close server");
                server.close();
            }
        }
    }
}
