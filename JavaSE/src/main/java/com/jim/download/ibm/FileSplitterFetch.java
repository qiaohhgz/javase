package com.jim.download.ibm;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/9/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.net.*;

public class FileSplitterFetch extends Thread {
    String sURL; //File URL
    long nStartPos; //File Snippet Start Position
    long nEndPos; //File Snippet End Position
    int nThreadID; //Thread's ID
    boolean bDownOver = false; //Downing is over
    boolean bStop = false; //Stop identical
    FileAccess fileAccess = null; //File Access interface

    public FileSplitterFetch(String sURL, String sName, long nStart, long nEnd, int id)
            throws IOException {
        this.sURL = sURL;
        this.nStartPos = nStart;
        this.nEndPos = nEnd;
        nThreadID = id;
        fileAccess = new FileAccess(sName, nStartPos);
    }

    @Override
    public void run() {
        while (nStartPos < nEndPos && !bStop) {
            try {
                URL url = new URL(sURL);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setRequestProperty("User-Agent", "NetFox");
                String sProperty = "bytes=" + nStartPos + "-" + nEndPos;
                httpConnection.setRequestProperty("RANGE", sProperty);
                Utility.log(sProperty);
                InputStream input = httpConnection.getInputStream();
//                logResponseHead(httpConnection);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos
                        && !bStop) {
                    nStartPos += fileAccess.write(b, 0, nRead);
//                    if (nThreadID == 1)
//                        Utility.log("nStartPos = " + nStartPos + ", nEndPos = " + nEndPos);
                }
                Utility.log("Thread " + nThreadID + " is over!");
                bDownOver = true;
                //nPos = fileAccess.write (b,0,nRead);
            } catch (Exception e) {
                System.err.println("Thread " + nThreadID + " is break. Error = " + e.getMessage());
                break;
            }
        }
    }

    // 打印回应的头信息
    public void logResponseHead(HttpURLConnection con) {
        for (int i = 1; ; i++) {
            String header = con.getHeaderFieldKey(i);
            if (header != null)
                Utility.log(header + " : " + con.getHeaderField(header));
            else
                break;
        }
    }

    public void splitterStop() {
        bStop = true;
    }
}
