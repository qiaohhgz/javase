package com.jim.download.v4;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/5/13
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestDownloadWithBreakPoint {
    @Test
    public void testDownload() throws Exception {
        DownloadWithBreakPoint manager = new DownloadWithBreakPoint();

        String urlStr = "http://xiazai.xiazaiba.com/Soft/T/Thunder_7.2.13.3882_XiaZaiBa.exe";
        manager.setSchedule(new FileSizeSchedule());
        manager.download(urlStr, null, "D:/", null);
    }
}
