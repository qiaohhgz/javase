package com.jim.download.v4;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/5/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IDownloadSchedule extends Runnable {

    void setContentLength(File downloadFile, int contentLength);
}
