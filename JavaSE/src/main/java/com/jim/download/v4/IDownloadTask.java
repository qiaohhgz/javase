package com.jim.download.v4;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/5/13
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IDownloadTask {
    File download(String urlStr, String charset, String downloadFileDir, String downloadFileName) throws Exception;
}
