package com.jim.download.v3;

import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/3/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestDownloadTask {
    String urlStr = "http://xiazai.xiazaiba.com/Soft/T/Thunder_7.2.13.3882_XiaZaiBa.exe";

    @Test
    public void testDownload() throws Exception {
        System.out.println("Testing Download Method");
        IDownloadTask manager = new DownloadTask();
        File file = manager.download(urlStr, "utf-8");
        assertNotNull(file);
    }

    @Test
    public void testOpenConnection() throws Exception {
        System.out.println("Testing OpenConnection Method");
        URL url = new URL(urlStr);
        url.openConnection().connect();
        url.openConnection().connect();
        url.openConnection().connect();
        url.openConnection().connect();
        url.openConnection().connect();

    }
}
