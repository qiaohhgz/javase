package com.jim.socket;

import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 8/27/13
 * Time: 2:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestAPI {
    @Test
    @Ignore
    public void testCall() throws Exception {
        String useProxy = "true";
        String host = "172.20.230.5";
        String port = "3128";
        System.getProperties().put("http.proxySet", useProxy);
        System.getProperties().put("http.proxyHost", host);
        System.getProperties().put("http.proxyPort", port);
        System.getProperties().put("https.proxySet", useProxy);
        System.getProperties().put("https.proxyHost", host);
        System.getProperties().put("https.proxyPort", port);

        String url = "http://172.20.230.77:6670/estimator/estimate/ping";
        PostMethod pm = new PostMethod(url);
        HttpClient httpclient = new HttpClient();

        String json = generateJSONForEstimator();

        RequestEntity entity = new StringRequestEntity(json);
        pm.setRequestEntity(entity);
        long tBefore = System.currentTimeMillis();
        int result = httpclient.executeMethod(pm);
        long tAfter = System.currentTimeMillis();
        System.out.println("Time: " + (tAfter - tBefore) + "s");
        System.out.println("result = " + result);
        if (result == HttpStatus.SC_OK) {
            System.out.println("get result ok");
        }
    }

    /**
     * @return
     * @see com.jim.socket.TestAPI#testCall()
     * @deprecated
     */
    private String generateJSONForEstimator() {
        List<String> params = new ArrayList<String>();
        params.add("jim");
        params.add("sally");
        params.add("jason");
        Gson g = new Gson();
        String json = g.toJson(params);
        return json;
    }
}
