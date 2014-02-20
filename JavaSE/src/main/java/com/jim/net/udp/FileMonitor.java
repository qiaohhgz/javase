package com.jim.net.udp;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 2/17/14
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileMonitor implements Runnable {
    private static final int DEFAULT_WAIT = 1000;
    private static final int RATE = 1024;
    private static final int NO_MAX_LENGTH = -1;
    private File file;
    private int maxLength;
    private int wait = DEFAULT_WAIT;

    public FileMonitor(File file) {
        this.file = file;
    }

    public FileMonitor(File file, int wait) {
        this.file = file;
        this.wait = wait;
    }

    public FileMonitor(File file, int wait, int maxLength) {
        this.file = file;
        this.maxLength = maxLength;
        this.wait = wait;
    }

    public void printFileInfo() {
        long length = file.length() / RATE;
        String unit;
        if (length >= RATE) {
            length = length / RATE;
            unit = "MB";
        } else if (length >= RATE * RATE) {
            length = length / RATE / RATE;
            unit = "GB";
        } else {
            unit = "KB";
        }
        String percent = "";
        if (maxLength != NO_MAX_LENGTH) {
            BigDecimal divide = new BigDecimal(length).divide(new BigDecimal(maxLength), 2, BigDecimal.ROUND_UP);
            percent = String.format(", (%s)", divide.doubleValue());
        }
        String info = String.format("Name:[%s], Size:[%s](%s)%s", file.getName(), length, unit, percent);
        System.out.println(info);
    }

    @Override
    public void run() {
        while (true) {
            try {
                printFileInfo();
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
