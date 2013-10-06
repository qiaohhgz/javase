package com.jim.util.proxy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/19/13
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProxyUtil {
    /**
     * 获取系统配置句柄
     */
    private static final Properties prop = System.getProperties();


    static {
        List<Proxy> proxyList = MyProxy.getSystemProxyList();
        for (Proxy proxy : proxyList) {
            MyProxy.printProxy(proxy, System.out);
        }
    }

    /**
     * 初始化代理 from Internet Settings
     */
    public static void initProxy() {

    }

    /**
     * 初始化代理
     *
     * @param proxyServer form Internet
     */
    public static void initProxy(String proxyServer) {
        System.out.println("proxyServer:" + proxyServer + "\nlength:" + proxyServer.split(";").length);

        String[] proxy = proxyServer.split("=|;|:");
        if (proxy.length == 1)// IP地址不同,但端口默认的代理初始化
        {
            initProxy(proxyServer);
            initProxyFtp(proxyServer, "2121");
            initProxyGopher(proxyServer, "808");

            initProxyHttp(proxyServer, "808");
            initProxyHttps(proxyServer, "443");

            initProxySocks(proxyServer, "1080");
        } else if (proxy.length == 2) {
            initProxy(proxy[0], proxy[1]);    // 统一代理初始化,所有协议使用相同的代理服务器
        } else {
            for (int i = 0; i < proxy.length; i = i + 3) {
                initProxy(proxy[i], proxy[i + 1], proxy[i + 2]);        // 独立代理初始化
            }
        }
    }

    /**
     * 独立代理初始化
     */
    private static void initProxy(String proxyPotocol, String proxyIP, String proxyPort) {
        System.out.println("proxyPotocol:" + proxyPotocol);
        String[] potocol = {"ftp", "gopher", "http", "https", "socks"};

        if (proxyPotocol.equals(potocol[0])) {
            initProxyFtp(proxyIP, proxyPort);        // 初始化FTP代理
        } else if (proxyPotocol.equals(potocol[1])) {
            initProxyGopher(proxyIP, proxyPort);    // 初始化GOPHER代理
        } else if (proxyPotocol.equals(potocol[2])) {
            initProxyHttp(proxyIP, proxyPort);        // 初始化HTTP代理
        } else if (proxyPotocol.equals(potocol[3])) {
            initProxyHttps(proxyIP, proxyPort);        // 初始化HTTPS代理
        } else if (proxyPotocol.equals(potocol[4])) {
            initProxySocks(proxyIP, proxyPort);        // 初始化SOCKS代理
        }
    }

    /**
     * 初始化FTP代理
     */
    private static void initProxyFtp(String proxyIP, String proxyPort) {
        // 使用ftp代理服务器的主机、端口以及不需要使用ftp代理服务器的主机
        prop.setProperty("ftp.proxyHost", proxyIP);
        prop.setProperty("ftp.proxyPort", proxyPort);
        prop.setProperty("ftp.nonProxyHosts", "localhost|10.10.*");
    }

    /**
     * 初始化GOPHER代理
     */
    private static void initProxyGopher(String proxyIP, String proxyPort) {
        prop.setProperty("gopher.proxyHost", proxyIP);
        prop.setProperty("gopher.proxyPort", proxyPort);
    }

    /**
     * 初始化HTTP代理
     */
    private static void initProxyHttp(String proxyIP, String proxyPort) {
        // 设置http访问要使用的代理服务器的地址
        prop.setProperty("http.proxyHost", proxyIP);
        // 设置http访问要使用的代理服务器的端口
        prop.setProperty("http.proxyPort", proxyPort);
        // 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
        prop.setProperty("http.nonProxyHosts", "localhost|10.10.*");
    }

    /**
     * 初始化HTTPS代理
     */
    private static void initProxyHttps(String proxyIP, String proxyPort) {
        // 设置安全访问使用的代理服务器地址与端口
        // 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
        prop.setProperty("https.proxyHost", proxyIP);
        prop.setProperty("https.proxyPort", proxyPort);
        // 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
        prop.setProperty("http.nonProxyHosts", "localhost|10.10.*");
    }

    /**
     * 初始化SOCKS代理
     */
    private static void initProxySocks(String proxyIP, String proxyPort) {
        // socks代理服务器的地址与端口
        prop.setProperty("socksProxyHost", proxyIP);
        prop.setProperty("socksProxyPort", proxyPort);
    }

    /**
     * 统一代理初始化
     */
    public static void initProxy(String IP, String port) {
        initProxy(IP);
        initProxyFtp(IP, port);
        initProxyGopher(IP, port);

        initProxyHttp(IP, port);
        initProxyHttps(IP, port);

        initProxySocks(IP, port);
    }

    /**
     * 使用代理
     */
    private static void testProxy() throws IOException {
        /** 使用代理连接网络 */
        URL url = new URL("http://www.baidu.com/");
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(3000);

        InputStream in = conn.getInputStream();
        BufferedInputStream bin = new BufferedInputStream(in);
        byte[] buf = new byte[1024]; // 缓存连网获得的数据
        while (bin.read(buf) > 0) {
            System.out.print(new String(buf, "GBK"));
        }
        System.out.println();
    }

    /**
     * for debugging.
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        initProxy(); // 初始化代理
        testProxy(); // 使用代理
    }
}
