package com.jim.download.v4;

import org.apache.commons.httpclient.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/5/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDownloadWithBreakPointTemplate extends AbstractDownloadTask {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int DEFAULT_THREAD_NUM = 5;
    public static final long DEFAULT_SLEEP_SECONDS = 5;
    public static final String DEFAULT_TEMP_FILE_SUFFIX = ".temp";
    private String urlStr;
    private String charset;
    private String downloadFileDir;
    private String downloadFileName;
    private File downloadFile;
    private File downloadConfigFile;
    private String suffix;
    private IDownloadSchedule schedule;

    protected void checkAndSetParams(String urlStr, String charset, String downloadFileDir, String downloadFileName) throws Exception {
        if (urlStr == null) {
            throw new NullPointerException("");
        }

        if (charset == null) {
            this.charset = DEFAULT_CHARSET;
        } else {
            this.charset = charset;
        }
        logger.info("Charset = " + this.charset);

        if (downloadFileDir == null) {
            throw new NullPointerException("Download file dir cannot null.");
        } else {
            logger.info("Download File Dir = " + downloadFileDir);
        }
        if (!new File(downloadFileDir).isDirectory()) {
            throw new Exception("Download file dir is not folder.");
        }

        if (downloadFileName == null) {
            this.downloadFileName = urlStr.substring(urlStr.lastIndexOf("/") + 1,
                    urlStr.lastIndexOf("?") > 0 ? urlStr.lastIndexOf("?")
                            : urlStr.length());
            if ("".equalsIgnoreCase(this.downloadFileName)) {
                this.downloadFileName = UUID.randomUUID().toString();
            }
        }
        logger.info("FileName = " + this.downloadFileName);

        if (suffix == null) {
            this.suffix = DEFAULT_TEMP_FILE_SUFFIX;
        }
        logger.info("Temp file suffix = " + this.suffix);

        if (downloadFileDir == null) {
            throw new NullPointerException("");
        }
    }

    protected void testConnect(URLConnection conn) throws IOException {
        logger.info("connecting...");
        conn.connect();//test connect
        logger.info("connected");
    }

    @Override
    public final File download(String urlStr, String charset, String downloadFileDir, String downloadFileName) throws Exception {
        checkAndSetParams(urlStr, charset, downloadFileDir, downloadFileName);

        HttpURLConnection conn = getHTTPURLConnection(urlStr);
        try {
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            printHeader(headerFields);

            int responseCode = conn.getResponseCode();
            logger.info("responseCode = " + responseCode);
            if (!checkResponseStatus(responseCode)) {
                logger.info("conn.getResponseMessage() = " + conn.getResponseMessage());
                throw new Exception(conn.getResponseMessage());
            }
            switch (responseCode) {
                case HttpStatus.SC_OK:
                    beginTask(conn);
                    break;
                case HttpStatus.SC_PARTIAL_CONTENT:
                    continueTask(conn);
                    break;
            }
            return downloadFile;
        } finally {
            if (null != conn) {
                logger.info("disconnect");
                conn.disconnect();
            }
        }
    }

    private void beginTask(HttpURLConnection conn) throws Exception {
        logger.debug("begin task");
        downloadConfigFile = new File(this.downloadFileDir, this.downloadFileName + suffix);
        if (downloadConfigFile.exists()) {
            logger.info("config file is exists path = " + downloadConfigFile.getAbsolutePath());
            continueTask(conn);
            return;
        }
        downloadFile = new File(this.downloadFileDir, this.downloadFileName);
        logger.info("file absolute path = " + downloadFile.getAbsolutePath());
        int contentLength = conn.getContentLength();
        if (downloadFile.exists()) {
            if (contentLength == this.downloadFile.length()) {
                logger.info("download file is exists");
                return;
            } else {
                deleteFiles(this.downloadFile);
            }
        }

        InputStream in = null;
        FileOutputStream fileStream = null;
        RandomAccessFile configStream = null;
        Exception ex = null;
        try {
            in = conn.getInputStream();
            fileStream = new FileOutputStream(downloadFileName);
            configStream = new RandomAccessFile(downloadConfigFile, "rw");
            byte[] bs = new byte[1024];
            int finishBytes = 0;

            startSchedule(getDownloadFile(), contentLength);
            for (int len = in.read(bs); len != -1; len = in.read(bs)) {
                fileStream.write(bs);
                finishBytes += len;
                configStream.seek(0);
                configStream.writeLong(finishBytes);
                if ((finishBytes % 4096) == 0) {
                    fileStream.flush();
                }
            }
            logger.info("download successful. file = " + downloadFile.getAbsolutePath());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            ex = e;
        } finally {
            close(fileStream, configStream, in);
            deleteFiles(downloadConfigFile);
            if (ex != null) {
                deleteFiles(downloadFile);
            }
        }
    }

    protected void startSchedule(File downloadFile, int contentLength) {
        if (this.getSchedule() == null) {
            return;
        }
        logger.info("start schedule " + getSchedule().getClass());
        getSchedule().setContentLength(downloadFile, contentLength);
        Thread t = new Thread(getSchedule());
        t.start();
    }

    private void close(Closeable... closes) throws IOException {
        for (Closeable obj : closes) {
            if (null != obj) {
                logger.debug(obj.getClass() + ".close()");
                obj.close();
            }
        }
    }

    private void deleteFiles(File... files) throws IOException {
        for (File file : files) {
            if (file != null && file.exists()) {
                if (file.delete()) {
                    logger.info(String.format("Deleted file = %s", file.getAbsolutePath()));
                } else {
                    logger.warn(String.format("file can not deleted path = %s", file.getAbsolutePath()));
                }
            }
        }
    }

    private void continueTask(HttpURLConnection conn) throws Exception {
        logger.debug("continue task");
        RandomAccessFile config = new RandomAccessFile(getDownloadConfigFile(), "rw");


    }

    protected abstract void printHeader(Map<String, List<String>> headerFields);

    private boolean checkResponseStatus(int responseCode) {
        return responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_PARTIAL_CONTENT;
    }

    protected HttpURLConnection getHTTPURLConnection(String urlStr) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();

        setHeader(conn);
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(10000);

        testConnect(conn);

        return conn;
    }

    protected void setHeader(URLConnection con) {
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
        con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
        con.setRequestProperty("Accept-Encoding", "aa");
        con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        con.setRequestProperty("Keep-Alive", "300");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");
        con.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
        con.setRequestProperty("Cache-Control", "max-age=0");
        con.setRequestProperty("Referer", "http://www.skycn.com/soft/14857.html");
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getDownloadFileDir() {
        return downloadFileDir;
    }

    public void setDownloadFileDir(String downloadFileDir) {
        this.downloadFileDir = downloadFileDir;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public File getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(File downloadFile) {
        this.downloadFile = downloadFile;
    }

    public File getDownloadConfigFile() {
        return downloadConfigFile;
    }

    public void setDownloadConfigFile(File downloadConfigFile) {
        this.downloadConfigFile = downloadConfigFile;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public IDownloadSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(IDownloadSchedule schedule) {
        this.schedule = schedule;
    }
}
