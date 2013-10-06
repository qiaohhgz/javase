package com.jim.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloadBreakPointDemo {
    public static final Logger log = Logger.getLogger(FileDownloadBreakPointDemo.class);
    final static String location = "http://dl.iteye.com/topics/download/3aceb91e-d7e7-3212-9b31-5bc02b10ebca";
    final static String toLocation = "F:/a.zip";

    public void run() throws Exception {
        final File file = new File(toLocation);

        long pos = 0L;

        if (file.exists()) {
            pos = file.length();
            log.debug("FileLength:" + pos);
        }

        URL url = new URL(location);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        conn.setRequestProperty("User-Agent", "NetFox");
        conn.setRequestProperty("RANGE", "bytes=" + pos);

        InputStream is = conn.getInputStream();

        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        raf.seek(pos);

        byte[] bs = new byte[1024];
        int sum = 0;
        log.debug("Begin write...");
        while ((sum = is.read(bs)) != -1) {
            raf.write(bs, 0, sum);
        }
        raf.close();
        is.close();
        log.debug("End write");
    }
}
