package com.jim.download.v3;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/3/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDownloadTask implements IDownloadTask {
    protected final Logger logger = Logger.getLogger(getClass());
}
