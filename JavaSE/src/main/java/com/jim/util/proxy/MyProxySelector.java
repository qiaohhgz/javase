package com.jim.util.proxy;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/19/13
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyProxySelector extends ProxySelector {
    // Keep a reference on the previous default
    ProxySelector defaultProxySelector = null;

    /*
    * Inner class representing a Proxy and a few extra data
    */
    class InnerProxy {
        Proxy proxy;
        SocketAddress address;
        // How many times did we fail to reach this proxy?
        int failedCount = 0;

        InnerProxy(InetSocketAddress address) {
            this.address = address;
            proxy = new Proxy(Proxy.Type.HTTP, address);
        }

        SocketAddress address() {
            return address;
        }

        Proxy toProxy() {
            return proxy;
        }

        int failed() {
            return ++failedCount;
        }
    }

    /*
    * A list of proxies, indexed by their address.
    */
    HashMap<SocketAddress, InnerProxy> proxies = new HashMap<SocketAddress, InnerProxy>();

    MyProxySelector(ProxySelector def) {
        // Save the previous default
        defaultProxySelector = def;

        // Populate the HashMap (List of proxies)
        InnerProxy i = new InnerProxy(new InetSocketAddress("webcache1.example.com", 8080));
        proxies.put(i.address(), i);
        i = new InnerProxy(new InetSocketAddress("webcache2.example.com", 8080));
        proxies.put(i.address(), i);
        i = new InnerProxy(new InetSocketAddress("webcache3.example.com", 8080));
        proxies.put(i.address(), i);
    }

    /*
    * This is the method that the handlers will call.
    * Returns a List of proxy.
    */
    public java.util.List<Proxy> select(URI uri) {
        // Let's stick to the specs.
        if (uri == null) {
            throw new IllegalArgumentException("URI can't be null.");
        }

        /*
        * If it's a http (or https) URL, then we use our own
        * list.
        */
        String protocol = uri.getScheme();
        if ("http".equalsIgnoreCase(protocol) ||
                "https".equalsIgnoreCase(protocol)) {
            ArrayList<Proxy> l = new ArrayList<Proxy>();
            for (InnerProxy p : proxies.values()) {
                l.add(p.toProxy());
            }
            return l;
        }

        /*
        * Not HTTP or HTTPS (could be SOCKS or FTP)
        * defer to the default selector.
        */
        if (defaultProxySelector != null) {
            return defaultProxySelector.select(uri);
        } else {
            ArrayList<Proxy> l = new ArrayList<Proxy>();
            l.add(Proxy.NO_PROXY);
            return l;
        }
    }

    /*
    * Method called by the handlers when it failed to connect
    * to one of the proxies returned by select().
    */
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        // Let's stick to the specs again.
        if (uri == null || sa == null || ioe == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        }

        /*
        * Let's lookup for the proxy
        */
        InnerProxy p = proxies.get(sa);
        if (p != null) {
            /*
            * It's one of ours, if it failed more than 3 times
            * let's remove it from the list.
            */
            if (p.failed() >= 3)
                proxies.remove(sa);
        } else {
            /*
            * Not one of ours, let's delegate to the default.
            */
            if (defaultProxySelector != null)
                defaultProxySelector.connectFailed(uri, sa, ioe);
        }
    }
}
