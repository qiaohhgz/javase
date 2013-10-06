package com.jim.util.runtime;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/29/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchProcess extends AbstractSearchProcess {
    @Override
    protected void getPIDByPort(int port) throws IOException {
        execute(String.format("cmd.exe netstat -aon|findstr \"%d\"", port), System.out);
    }

    @Override
    protected void getProcessByPID(int pid) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
