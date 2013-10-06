package com.jim.download.v4;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/5/13
 * Time: 8:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileSizeSchedule implements IDownloadSchedule {
    protected final Logger logger = Logger.getLogger(getClass());
    private File file;
    private int contentLength;
    private long fileLength;

    @Override
    public void setContentLength(File file, int contentLength) {
        this.file = file;
        this.contentLength = contentLength;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.fileLength = getFile().length();
                int percent;
                percent = Double.valueOf(getFileLength() * 100D / getContentLength()).intValue();
                logger.debug(percent + "%");
                Thread.sleep(500);
            }
        } catch (Exception ex) {

        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }
}
