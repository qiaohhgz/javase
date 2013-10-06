package com.jim;

import org.junit.Test;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/14/13
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestUseSystemProxy {
    @Test
    public void testUseSystemProxy() throws Exception {
        String useSystemProxies = System.getProperty("java.net.useSystemProxies");
        assertNotNull(useSystemProxies);
        System.out.println(useSystemProxies);
        assertEquals(Boolean.TRUE.toString(), useSystemProxies);
        System.setProperty("java.net.useSystemProxies", "true");
        List<Proxy> list = null;
        try {
            list = ProxySelector.getDefault().select(new URI("http://www.baidu.com"));
            assertNotNull(list);
            Proxy proxy = list.get(0);
            assertNotNull(proxy);
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrintSystemProxy() throws Exception {

    }

    @Test
    public void testSunNetSystemProxyInterface() throws Exception {

    }
}
