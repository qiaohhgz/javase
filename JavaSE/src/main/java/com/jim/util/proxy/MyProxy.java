package com.jim.util.proxy;

import common.Logger;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Properties;

public class MyProxy {
    protected static final Logger log = Logger.getLogger(MyProxy.class);
    private static final String DEFAULT_SELECTOR_URL = "http://www.baidu.com";
    private static String[] HOSTS = new String[]{"http", "https", "smtp", "-Dhttp", "-Dhttps"};
    private static String[] ITEMS = new String[]{"proxySet", "proxyHost", "proxyPort"};

    public static void applyProxy() {
        Proxy proxy = getSystemProxy(new DefaultProxyFilter());
        if (proxy == null) {
            log.warn("Can not found system proxy.");
            return;
        }
        if (proxy == Proxy.NO_PROXY) {
            log.warn("system not set proxy.");
            return;
        }
        InetSocketAddress address = (InetSocketAddress) proxy.address();
        if (address == null) {
            throw new NullPointerException("address can't be null.");
        }
        String host = address.getHostName();
        String port = String.valueOf(address.getPort());
        String proxySet = Boolean.TRUE.toString();
        log.info(String.format("Proxy %s:%s.", host, port));

        String[] values = new String[]{proxySet, host, port};
        Properties pro = System.getProperties();
        for (int i = 0; i < HOSTS.length; i++) {
            for (int j = 0; j < ITEMS.length; j++) {
                String key = String.format("%s.%s", HOSTS[i], ITEMS[j]);
                String value = values[j];
                pro.put(key, value);
                log.debug(key + "=" + pro.get(key));
            }
        }
    }

    public static void setUseSystemProxy(boolean use) {
        System.setProperty("java.net.useSystemProxies", Boolean.valueOf(use).toString());
    }

    public static List<Proxy> getSystemProxyList() {
        setUseSystemProxy(true);
        log.info("Detecting proxies");
        List<Proxy> l = null;
        try {
            l = ProxySelector.getDefault().select(URI.create(DEFAULT_SELECTOR_URL));
        } catch (Exception e) {
            log.error("Error getting system proxy list.", e);
        }
        setUseSystemProxy(false);
        return l;
    }

    @Deprecated
    public static Proxy getSystemProxy() {
        List<Proxy> proxyList = getSystemProxyList();
        if (proxyList != null) {
            if (proxyList.size() == 0) {
                log.warn("System proxy list size is zero.");
            } else if (proxyList.size() > 1) {
                log.warn("System more than one agent.");
            } else {
                return proxyList.get(0);
            }
        }
        return null;
    }

    public static Proxy getSystemProxy(ProxyFilter proxyFilter) {
        if (proxyFilter == null) {
            throw new NullPointerException("Proxy filter can't be null.");
        }
        List<Proxy> proxyList = getSystemProxyList();
        if (proxyList != null) {
            if (proxyList.size() > 1) {
                log.warn(String.format("System more than one agent (size = %d).", proxyList.size()));
            }
            for (Proxy proxy : proxyList) {
                if (proxyFilter.accept(proxy)) {
                    return proxy;
                }
            }
        }
        return null;
    }

    public static void setProxyToEnv(Proxy proxy) {
        if (proxy == null) {
            throw new NullPointerException("Proxy can't be null.");
        }
        log.info("Proxy hostname : " + proxy.type());

        InetSocketAddress address = (InetSocketAddress) proxy.address();

        if (address == null) {
            log.info("No Proxy");
        } else {
            log.info("proxy hostname : " + address.getHostName());
            System.setProperty("http.proxyHost", address.getHostName());
            log.info("proxy port : " + address.getPort());
            System.setProperty("http.proxyPort", Integer.toString(address.getPort()));
        }
    }

    public static void printProxy(Proxy proxy, PrintStream out) {
        InetSocketAddress address = (InetSocketAddress) proxy.address();
        String hostName = address.getHostName();
        int port = address.getPort();
        out.println(String.format("Proxy %s:%d", hostName, port));
    }
}
