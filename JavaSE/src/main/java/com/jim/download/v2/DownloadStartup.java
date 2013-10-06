package com.jim.download.v2;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/3/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadStartup {

    private static final String encoding = "utf-8";
    public static void main(String[] args) {

        DownloadTask downloadManager = new DownloadTask();

        String urlStr = "http://xiazai.xiazaiba.com/Soft/T/Thunder_7.2.13.3882_XiaZaiBa.exe";
        downloadManager.setSleepSeconds(5);
        String downladFileName = downloadManager.download(urlStr, encoding);
        System.out.println("Download file is " + downladFileName + ".");
    }
}
