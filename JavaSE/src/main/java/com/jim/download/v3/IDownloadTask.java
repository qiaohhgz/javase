package com.jim.download.v3;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/3/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IDownloadTask {
    File download(String urlStr, String charset) throws Exception;
}
