package com.jim.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class FileUtils {

    private static final Logger log = Logger.getLogger(FileUtils.class);

    public static int getFileLines(File file) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(file.getUsableSpace());
        int lineNumber = lnr.getLineNumber() + 1;
        lnr.close();
        return lineNumber;
    }

    public static List<String> readFileToList(File file, int start, int end) throws IOException {
        List<String> result = new ArrayList<String>();
        Reader reader = null;
        LineNumberReader lineReader = null;
        if (start > end || start < 0) {
            return result;
        }
        int lines = 0;
        String lineValue = null;
        try {
            reader = new FileReader(file);
            lineReader = new LineNumberReader(reader);
            while (lineReader.ready()) {
                lines = lineReader.getLineNumber();
                if (lines < start) {
                    lineReader.readLine();
                } else if (lines >= end) {
                    break;
                } else {
                    lineValue = lineReader.readLine();
                    //					log.info(lineValue);
                    result.add(lineValue);
                }
            }
            return result;
        } catch (IOException e) {
            throw e;
        } finally {
            if (null != lineReader) {
                lineReader.close();
            }
        }
    }

    /**
     * 实现得到路径下对应所有的文件及文件夹
     *
     * @param path
     * @return
     */
    public static File[] fileList(String path) {
        return new File(path).listFiles();
    }

    /**
     * 实现获取得到路径下符合条件的文件及文件夹
     *
     * @param path
     * @param cond
     * @return
     */
    public static File[] fileList(String path, final String cond) {
        File file = new File(path);
        return file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.indexOf(cond) >= 0;
            }
        });
    }

    /**
     * 实现读取文件功能
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String readFile(String path) throws IOException {
        File file = new File(path);
        StringBuffer strBuffer = new StringBuffer();
        Reader reader = null;
        BufferedReader breader = null;
        try {
            reader = new FileReader(file);
            breader = new BufferedReader(reader);
            String strLine = null;
            while ((strLine = breader.readLine()) != null) {
                strBuffer.append(strLine + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (breader != null) {
                breader.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
        return strBuffer.toString();
    }

    /**
     * 实现写文件功能
     *
     * @param path
     * @param content
     * @throws IOException
     */
    public static void writeFile(String path, String content) throws IOException {
        File file = new File(path);
        Writer writer = null;
        BufferedWriter bWriter = null;
        PrintWriter out = null;
        try {
            writer = new FileWriter(file);
            bWriter = new BufferedWriter(writer);
            out = new PrintWriter(bWriter);
            out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
            if (bWriter != null)
                bWriter.close();
            if (writer != null)
                writer.close();
        }
    }

    /**
     * 实现创建文件功能
     *
     * @param path
     * @return
     */
    public static boolean createFile(String path) throws IOException {
        File file = new File(path);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new IOException("Error:createNewFile\nPath:" + path, e);
        }
    }

    /**
     * 实现创建多个文件夹功能
     *
     * @param path
     * @return
     */
    public static boolean mkdirs(String path) {
        File file = new File(path);
        return file.mkdirs();
    }

    /**
     * 实现删除文件和文件夹功能
     *
     * @param path
     * @return
     */
    public static boolean delete(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            File[] listFiles = file.listFiles();
            for (File f : listFiles) {
                f.delete();
            }
//			file.delete();
        }
        return true;
    }

    public static String getFileByClassLoader(String sourceName) {
        return FileUtils.class.getClassLoader().getResource(sourceName).getFile();
    }
}
