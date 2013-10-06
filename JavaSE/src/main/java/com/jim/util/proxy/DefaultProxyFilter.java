package com.jim.util.proxy;

import java.net.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/16/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultProxyFilter implements ProxyFilter {

    @Override
    public boolean accept(Proxy proxy) {
        return proxy != Proxy.NO_PROXY;
    }
}
