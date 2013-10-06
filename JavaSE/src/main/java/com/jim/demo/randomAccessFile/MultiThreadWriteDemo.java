package com.jim.demo.randomAccessFile;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/4/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MultiThreadWriteDemo {
    protected final Logger logger = Logger.getLogger(getClass());

    public void write(File file, int threadNum) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        List<FileWrite> writes = new ArrayList<FileWrite>();
        long seekLen = 15L * threadNum;
        for (int i = 0; i < threadNum; i++) {
            RandomAccessFile rw = new RandomAccessFile(file, "rw");
            logger.info("seekLen = " + seekLen);
            rw.seek(seekLen);
            FileWrite write = new FileWrite("HelloWorld" + i, rw);
            writes.add(write);
            seekLen -= 15L;
        }
        for (FileWrite write : writes) {
            write.start();
        }
        boolean allIsFinish = true;
        while (allIsFinish) {
            allIsFinish = false;
            for (FileWrite write : writes) {
                if (!write.isFinish()) {
                    allIsFinish = true;
                } else {
                    logger.info("write.getStatus() = " + write.getStatus());
                }
            }
        }
        logger.info("all write finish.");
    }

    class FileWrite extends Thread {
        protected final Logger logger = Logger.getLogger(getClass());
        private final int STATUS_ERROR = 500;
        private final int STATUS_OK = 200;
        private String data;
        private RandomAccessFile raf;
        private int status;

        FileWrite(String data, RandomAccessFile raf) {
            this.data = data;
            this.raf = raf;
        }

        @Override
        public void run() {
            try {
                char[] chars = data.toCharArray();
                for (char c : chars) {
                    raf.write(c);
                    logger.info("this.getName() = " + this.getName());
                    Thread.sleep(50);
                }
                this.status = STATUS_OK;
            } catch (Exception ex) {
                logger.info("ex.getMessage() = " + ex.getMessage(), ex);
                this.status = STATUS_ERROR;
            } finally {
                try {
                    if (null != raf) {
                        raf.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

        public boolean isOK() {
            return status == STATUS_OK;
        }

        public boolean isError() {
            return status == STATUS_ERROR;
        }

        public boolean isFinish() {
            return status != 0;
        }

        public int getStatus() {
            return status;
        }
    }
}