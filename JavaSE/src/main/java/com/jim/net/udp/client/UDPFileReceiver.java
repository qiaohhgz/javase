package com.jim.net.udp.client;

import com.jim.net.udp.FileMonitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPFileReceiver {
    public static final int PACKAGE_SIZE = 1024;
    private int port = 1220;
    private DatagramSocket socket;

    public UDPFileReceiver() throws SocketException {
        socket = new DatagramSocket(port);
        socket.setSoTimeout(600000);
        System.out.println("start client");
    }

    public void reciveData() throws FileNotFoundException {
        File newfile = new File("D:\\android-sdk.rar");
//        newfile = new File("D:\\test.txt");
        new Thread(new FileMonitor(newfile, 3000)).start();
        byte[] buf = new byte[PACKAGE_SIZE];
        FileOutputStream fos = new FileOutputStream(newfile);
        int count = 0;
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, PACKAGE_SIZE);
            try {
                socket.receive(packet);
                count++;
                String str = new String(packet.getData(), 0, packet.getLength());
                if (str.equalsIgnoreCase("end")) {
                    System.out.println("end");
                    break;
                } else {
                    fos.write(packet.getData(), 0, packet.getLength());
                }
            } catch (Exception e) {
                try {
                    socket.close();
                    fos.flush();
                    fos.close();
                    System.err.println(e.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
        System.out.println("write (" + count + ") package");
    }

    public static void main(String[] args) throws FileNotFoundException, SocketException {
        new UDPFileReceiver().reciveData();
    }

}
