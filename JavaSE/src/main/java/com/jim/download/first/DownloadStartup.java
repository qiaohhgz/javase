package com.jim.download.first;

import com.jim.util.proxy.MyProxy;
import common.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/3/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadStartup {
    static Logger logger = Logger.getLogger(DownloadStartup.class);

    public static void main(String[] args) {
        MyProxy.applyProxy();
        String encoding = "utf-8";
        DownloadTask manager = new DownloadTask();

        String urlStr = "http://ftp.pconline.com.cn/e619cbece4a99646eccb702634a2972a/pub/download/201010/4tpy_installer_cn.exe";

        try {
            Integer.valueOf(encoding);
        } catch (Exception e) {
            logger.error(e);
        }

        manager.setSleepSeconds(5);
        manager.setFileDir("D:/MyDownload/");
        manager.setThreadNum(1);
        long before = System.currentTimeMillis();
        String fileName = manager.download(urlStr, encoding);
        long after = System.currentTimeMillis();
        //use time = 42744
        logger.info("use time = " + (after - before));
        logger.info("Download file is " + fileName + ".");
    }
}
