package com.jim.util.proxy;

import java.net.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/16/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ProxyFilter {
    boolean accept(Proxy proxy);
}
