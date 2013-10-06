package com.jim.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MyHelper {
    static final Logger log = Logger.getLogger(MyHelper.class);

    public static String getFileNameByUrl(String url) {
        String[] urls = url.split("/");
        return urls[urls.length - 1];
    }

    public static void write(InputStream is, OutputStream os) throws IOException {
        byte[] bs = new byte[1024];
        int sum = 0;
        while ((sum = is.read(bs)) != -1) {
            os.write(bs, 0, sum);
        }
        is.close();
        os.flush();
        os.close();
    }

    public static void writeAutoSpeed(InputStream is, OutputStream os) throws IOException {
        byte[] bs = new byte[1024];
        int sum = 0;
        while ((sum = is.read(bs)) != -1) {
            os.write(bs, 0, sum);
            if (sum == bs.length) {
                bs = new byte[bs.length + 1024];
            } else if (sum < bs.length - 1024) {
                bs = new byte[bs.length - 1024];
            }
            System.out.println(bs.length);
        }
        is.close();
        os.flush();
        os.close();
    }

    public static <T> List<T> array2List(T[] args) {
        if (args == null || args.length == 0) {
            throw new NullPointerException();
        }
        return Arrays.asList(args);
    }

    public static List<String> str2List(String str, String delimiter) {
        if (str == null || str.trim().length() == 0 || delimiter == null) {
            throw new NullPointerException();
        }
        return array2List(str.split(delimiter));
    }

    public static URL getResource(String name) {
        return Thread.currentThread().getContextClassLoader().getResource(name);
        // return MyHelper.class.getClassLoader().getResource("") + name;
    }

    public static boolean convertFileEncoding(File file, String source, String target) throws IOException {
        log.info(String.format("Converted %s -> %s %s", source, target, file.getAbsoluteFile()));
        char[] cs = new char[1024 * 4];
        File bakFile = new File(file.getPath() + ".bak");
        InputStreamReader in;
        if (source == null) {
            in = new InputStreamReader(new FileInputStream(file));
        } else {
            in = new InputStreamReader(new FileInputStream(file), source);
        }
        Reader r = new BufferedReader(in);
        Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bakFile), target));
        int len = 0;
        for (int read = r.read(cs); read != -1; read = r.read(cs)) {
            w.write(cs, 0, read);
            len += read;
            if (len % 1024 == 0) {
                log.debug("Flush");
                w.flush();
            }
        }
        r.close();
        w.close();

        log.debug("begin delete " + file.getAbsolutePath());
        if (file.delete()) {
            log.debug("delete successful.");
            bakFile.renameTo(file);
            log.debug("rename successful");
            return true;
        }
        return false;
    }

    public static String convertStringEncoding(String str, String source, String target)
            throws UnsupportedEncodingException {
        return new String(str.getBytes(source), target);
    }
}
