package com.jim.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConvertProjectEncoding implements FileFilter {
    private int sum = 0;
    private int num = 0;
    private String sourceCharset;
    private String targetCharset;
    private String[] filter;

    public void convert(String path, String sourceCharset, String targetCharset, String[] filter) throws FileNotFoundException {
        this.sourceCharset = sourceCharset;
        this.targetCharset = targetCharset;
        this.filter = filter;
        if (path == null || !(new File(path).exists())) {
            throw new FileNotFoundException(path);
        }
        convert(new File(path), this);

        System.out.println("总共转换" + sum + "个文件，成功转换" + num + "个文件");
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        if (file.isFile()) {
            if (filter == null) {
                return true;
            }
            for (String item : filter) {
                if (item.equals("*")) {
                    return true;
                }
                if (item.length() > 0 && file.getName().endsWith(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void convert(File file, FileFilter filter) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            try {
                sum++;
                boolean b = MyHelper.convertFileEncoding(file, sourceCharset, targetCharset);
                if (b) {
                    num++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File[] files = file.listFiles(filter);
            for (File item : files) {
                convert(item, filter);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = null;
        String[] filter = null;
        String source = null;
        String target = null;

        if (args == null || args.length == 0 || args[0].toLowerCase().trim().equals("help")) {
            System.out.println("path souceEncoding targetEncoding filterNames [.java|.txt ...]");
        } else {
            if (args[0] != null) {
                path = args[0];
            }
            if (args[1] != null) {
                source = args[1];
            }
            if (args[2] != null) {
                target = args[2];
            }
            if (args[3] != null) {
                filter = args[3].split("|");
                if (filter == null || filter.length == 0) {
                    return;
                }
            }
            ConvertProjectEncoding projectEncoding = new ConvertProjectEncoding();
            projectEncoding.convert(path, source, target, filter);
        }
    }
}
