package com.jim.pdf.first;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 10/8/13
 * Time: 6:00 AM
 * To change this template use File | Settings | File Templates.
 */
class FileUtil {
    static void deleteFile(String file) {
        File f = new File(file);
        if (f != null && f.exists()) {
            f.delete();
        }
    }
}
