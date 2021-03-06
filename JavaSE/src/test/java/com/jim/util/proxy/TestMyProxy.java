package com.jim.util.proxy;

import com.jim.util.FileUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

import static com.jim.util.proxy.MyProxy.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/15/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMyProxy {
    @Test(timeout = 3000)
    public void testConnection() throws Exception {
        System.out.println("Testing Connection Method");
//        System.getProperties().put("http.proxyHost", "172.20.230.5");
//        System.getProperties().put("http.proxyPort", 3128);
        URLConnection connection = new URL("http://www.baidu.com").openConnection();
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (reader.ready()) {
            System.out.println(reader.readLine());
        }
        reader.close();
    }

    @Test(timeout = 3000)
    public void testApplyProxy() throws Exception {
        applyProxy();
        try {
            URLConnection connection = new URL("http://www.bing.com").openConnection(Proxy.NO_PROXY);
            connection.setConnectTimeout(3000);
            connection.connect();
            fail("No Exception thrown.");
        } catch (Exception ex) {
            assertTrue(ex instanceof ConnectException);
        }
        URLConnection connection = new URL("http://www.bing.com").openConnection();
        connection.setConnectTimeout(3000);
        connection.connect();
    }

    @Test
    public void testGetSystemProxy() throws Exception {
        getSystemProxy();
        getSystemProxy(new DefaultProxyFilter());
        try {
            getSystemProxy(null);
            fail("No Exception thrown.");
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void testSetProxyToEnv() throws Exception {
        try {
            setProxyToEnv(null);
            fail("No Exception thrown.");
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
        setProxyToEnv(getSystemProxy(new DefaultProxyFilter()));
    }

    @Test
    public void testGooglePlacesAPI() throws Exception {
        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&sensor=false&key=AIzaSyAdre9pJy7cQ7kLiHVcRLHWeHzYv4rsrVY";
//        url = "https://maps.googleapis.com/maps/api/place/textsearch/xml?query=restaurants+in+Sydney&sensor=true&key=AIzaSyCh3D2TDQA4WL0uhDY0MUXgGqsfvIMPIOA";
        Proxy proxy = getSystemProxy(new DefaultProxyFilter());
        System.out.println("proxy.type.name = " + proxy.type().name());
        URLConnection conn = new URL(url).openConnection(proxy);
        conn.setConnectTimeout(100000);
        conn.setReadTimeout(100000);

        InputStream in = conn.getInputStream();
        List<String> strings = IOUtils.readLines(in);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testChar() throws Exception {
        for (char i = 'A'; i <= 'Z'; i++) {
            System.out.println(i);
        }

    }
}
