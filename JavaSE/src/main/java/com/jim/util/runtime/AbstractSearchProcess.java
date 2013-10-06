package com.jim.util.runtime;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/29/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSearchProcess extends AbstractRuntimeHelper {
    /**
     * Command netstat -aon|findstr "9050"
     * 根据端口号寻找进程号
     *
     * @param port
     * @throws IOException
     */
    protected abstract void getPIDByPort(int port) throws IOException;

    /**
     * Command tasklist|findstr "2016"
     * 根据进程号寻找进程名称
     *
     * @param pid
     * @throws IOException
     */
    protected abstract void getProcessByPID(int pid) throws IOException;
}
