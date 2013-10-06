package com.jim.demo.excel;

import com.jim.memory.JvmMemoryCheck;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 6/13/13
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestXSSFHelper {
    @Test
    @Ignore
    public void testLoad() throws Exception {
        XSSFHelper helper = new XSSFHelper();
        final JvmMemoryCheck memory = new JvmMemoryCheck();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                        memory.check();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        t.start();
        helper.load("E:/data.xlsx");
        t.stop();
    }
}
